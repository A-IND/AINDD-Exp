//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package de.metanome.algorithms.aspider.structures;

import de.metanome.algorithm_integration.AlgorithmExecutionException;
import de.metanome.algorithm_integration.input.DatabaseConnectionGenerator;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import de.metanome.algorithms.aspider.core.ASPIDER;
import de.metanome.algorithms.aspider.sorting.TPMMS;
import de.uni_potsdam.hpi.dao.DataAccessObject;
import de.uni_potsdam.hpi.utils.DatabaseUtils;
import de.uni_potsdam.hpi.utils.FileUtils;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntLinkedOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntListIterator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Attribute implements Comparable<Attribute>, Closeable {
    protected int attributeId;
    protected String currentValue;
    protected IntLinkedOpenHashSet referenced;
    protected IntLinkedOpenHashSet dependents;
    protected BufferedReader valueReader;

    public Attribute(int attributeId, List<String> attributeTypes, DatabaseConnectionGenerator inputGenerator, int inputRowLimit, DataAccessObject dao, String tableName, String attributeName, File tempFolder) throws AlgorithmExecutionException {
        this.attributeId = attributeId;
        int numAttributes = attributeTypes.size();
        this.referenced = new IntLinkedOpenHashSet(numAttributes);
        this.dependents = new IntLinkedOpenHashSet(numAttributes);

        for(int i = 0; i < numAttributes; ++i) {
            if (i != this.attributeId && DatabaseUtils.matchSameDataTypeClass((String)attributeTypes.get(i), (String)attributeTypes.get(this.attributeId))) {
                this.referenced.add(i);
                this.dependents.add(i);
            }
        }

        ResultSet resultSet = null;
        BufferedWriter writer = null;

        try {
            resultSet = inputGenerator.generateResultSetFromSql(dao.buildSelectDistinctSortedColumnQuery(tableName, attributeName, (String)attributeTypes.get(this.attributeId), inputRowLimit));
            writer = FileUtils.buildFileWriter(tempFolder.getPath() + File.separator + attributeId, false);
            boolean isFirstValues = true;

            while(resultSet.next()) {
                String value = resultSet.getString(1);
                if (value != null) {
                    value = value.replaceAll("\n", "\u0000");
                    if (!isFirstValues) {
                        writer.newLine();
                    }

                    writer.write(value);
                    isFirstValues = false;
                }
            }

            writer.flush();
            this.valueReader = FileUtils.buildFileReader(tempFolder.getPath() + File.separator + attributeId);
            this.currentValue = "";
            this.nextValue();
        } catch (SQLException var22) {
            var22.printStackTrace();
            throw new AlgorithmExecutionException(var22.getMessage());
        } catch (IOException var23) {
            var23.printStackTrace();
            throw new AlgorithmExecutionException(var23.getMessage());
        } finally {
            FileUtils.close(writer);
            DatabaseUtils.close(resultSet);

            try {
                DatabaseUtils.close(resultSet.getStatement());
            } catch (SQLException var21) {
            }

        }
    }

    public Attribute(int attributeId, List<String> attributeTypes, RelationalInputGenerator inputGenerator, int inputRowLimit, int relativeAttributeIndex, File tempFolder, long maxMemoryUsage, int memoryCheckFrequency, ColumnSize colSize) throws AlgorithmExecutionException {
        //文件读取的形式的构造函数
        this.attributeId = attributeId;
        int numAttributes = attributeTypes.size();
        this.referenced = new IntLinkedOpenHashSet(numAttributes);
        this.dependents = new IntLinkedOpenHashSet(numAttributes);

        for(int i = 0; i < numAttributes; ++i) {
            if (i != this.attributeId && DatabaseUtils.matchSameDataTypeClass((String)attributeTypes.get(i), (String)attributeTypes.get(this.attributeId))) {
                this.referenced.add(i);
                this.dependents.add(i);
            }
        }

        try {
            TPMMS.sortToDisk(inputGenerator, inputRowLimit, tempFolder.getPath() + File.separator + attributeId, relativeAttributeIndex, maxMemoryUsage, memoryCheckFrequency, colSize);
            this.valueReader = FileUtils.buildFileReader(tempFolder.getPath() + File.separator + attributeId);
            this.currentValue = "";
            this.nextValue();
        } catch (IOException var12) {
            var12.printStackTrace();
            throw new AlgorithmExecutionException(var12.getMessage());
        }
    }

    public int getAttributeId() {
        return this.attributeId;
    }

    public String getCurrentValue() {
        return this.currentValue;
    }

    public IntLinkedOpenHashSet getReferenced() {
        return this.referenced;
    }

    public IntLinkedOpenHashSet getDependents() {
        return this.dependents;
    }

    public void nextValue() throws IOException {
        if (this.currentValue != null) {
            this.currentValue = this.valueReader.readLine();
            if (this.currentValue == null) {
                this.close();
            }

        }
    }

    public void intersectReferenced(IntArrayList referencedAttributes, Int2ObjectOpenHashMap<Attribute> attributeMap) {
        IntListIterator referencedIterator = this.referenced.iterator();

        while(referencedIterator.hasNext()) {
            int referenced = referencedIterator.nextInt();
            if (!referencedAttributes.contains(referenced)) {
                ASPIDER.violationMatrix[this.attributeId][referenced]++;
                if (ASPIDER.violationMatrix[this.attributeId][referenced] > ASPIDER.maxViolationNum[this.attributeId]){
                    referencedIterator.remove();
                    ((Attribute)attributeMap.get(referenced)).removeDependent(this.attributeId);
                }
            }
        }

    }

    public void removeDependent(int dependent) {
        this.dependents.remove(dependent);
    }

    public boolean hasFinished() {
        if (this.currentValue == null) {
            return true;
        } else {
            return this.referenced.isEmpty() && this.dependents.isEmpty();
        }
    }

    public int hashCode() {
        return this.attributeId;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Attribute)) {
            return false;
        } else {
            Attribute other = (Attribute)obj;
            return this.compareTo(other) == 0;
        }
    }

    public String toString() {
        return "(" + this.attributeId + " - \"" + this.currentValue + "\")";
    }

    public int compareTo(Attribute other) {
        if (this.getCurrentValue() == null && other.getCurrentValue() == null) {
            if (this.getAttributeId() > other.getAttributeId()) {
                return 1;
            } else {
                return this.getAttributeId() < other.getAttributeId() ? -1 : 0;
            }
        } else if (this.getCurrentValue() == null) {
            return 1;
        } else if (other.getCurrentValue() == null) {
            return -1;
        } else {
            int order = this.getCurrentValue().compareTo(other.getCurrentValue());
            if (order == 0) {
                if (this.getAttributeId() > other.getAttributeId()) {
                    return 1;
                } else {
                    return this.getAttributeId() < other.getAttributeId() ? -1 : 0;
                }
            } else {
                return order;
            }
        }
    }

    public void close() {
        try {
            this.valueReader.close();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }
}

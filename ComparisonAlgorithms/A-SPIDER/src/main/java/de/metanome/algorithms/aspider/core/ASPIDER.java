//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package de.metanome.algorithms.aspider.core;

import de.metanome.algorithm_integration.AlgorithmConfigurationException;
import de.metanome.algorithm_integration.AlgorithmExecutionException;
import de.metanome.algorithm_integration.ColumnIdentifier;
import de.metanome.algorithm_integration.ColumnPermutation;
import de.metanome.algorithm_integration.input.DatabaseConnectionGenerator;
import de.metanome.algorithm_integration.input.InputGenerationException;
import de.metanome.algorithm_integration.input.RelationalInput;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import de.metanome.algorithm_integration.result_receiver.ColumnNameMismatchException;
import de.metanome.algorithm_integration.result_receiver.CouldNotReceiveResultException;
import de.metanome.algorithm_integration.result_receiver.InclusionDependencyResultReceiver;
import de.metanome.algorithm_integration.results.InclusionDependency;
import de.metanome.algorithms.aspider.structures.Attribute;
import de.metanome.algorithms.aspider.structures.ColumnSize;
import de.uni_potsdam.hpi.dao.DataAccessObject;
import de.uni_potsdam.hpi.utils.CollectionUtils;
import de.uni_potsdam.hpi.utils.DatabaseUtils;
import de.uni_potsdam.hpi.utils.FileUtils;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntListIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class ASPIDER {
    protected DatabaseConnectionGenerator databaseConnectionGenerator = null;
    protected RelationalInputGenerator[] fileInputGenerator = null;
    protected InclusionDependencyResultReceiver resultReceiver = null;
    protected DataAccessObject dao = null;
    protected String[] tableNames = null;
    protected String databaseName = null;
    protected String tempFolderPath = "A-SPIDER_temp";
    protected boolean cleanTemp = true;
    protected int inputRowLimit = -1;
    protected int memoryCheckFrequency = 100;
    protected int maxMemoryUsagePercentage = 60;
    protected File tempFolder = null;
    protected int numColumns = -1;
    protected long availableMemory;
    protected long maxMemoryUsage;
    protected long statisticTime = -1L;
    protected long loadTime = -1L;
    protected long compareTime = -1L;
    protected long outputTime = -1L;
    protected int[] tableColumnStartIndexes = null;
    protected List<String> columnNames = null;
    /////
//    protected List<Integer> sizeOfColumn =  null;
    public static int[][] violationMatrix;
    protected int violationRate = 0;
    protected float denominator = 10000;
    public static int maxViolationNum[];
    private List<InclusionDependency> result = null;
    ////
    protected List<String> columnTypes = null;
    protected Int2ObjectOpenHashMap<Attribute> attributeId2attributeObject = null;
    protected PriorityQueue<Attribute> attributeObjectQueue = null;
    protected int numUnaryINDs = 0;

    public ASPIDER() {
    }

    public String toString() {
        String input = "-";
        if (this.databaseConnectionGenerator != null) {
            input = this.databaseConnectionGenerator.getClass().getName();
        } else if (this.fileInputGenerator != null) {
            input = this.fileInputGenerator[0].getClass().getName() + " (" + this.fileInputGenerator.length + ")";
        }

        return "SPIDER: \r\n\tinput: " + input + "\r\n\tinputRowLimit: " + this.inputRowLimit + "\r\n\tresultReceiver: " + (this.resultReceiver != null ? this.resultReceiver.getClass().getName() : "-") + "\r\n\tdao: " + (this.dao != null ? this.dao.getClass().getName() : "-") + "\r\n\ttableNames: " + (this.tableNames != null ? CollectionUtils.concat(this.tableNames, ", ") : "-") + "\r\n\tnumColumns: " + this.numColumns + "\r\n\tdatabaseName: " + this.databaseName + "\r\n\ttempFolderPath: " + this.tempFolder.getPath() + "\r\n\tmemoryCheckFrequency: " + this.memoryCheckFrequency + "\r\n\tmaxMemoryUsagePercentage: " + this.maxMemoryUsagePercentage + "%\r\n\tavailableMemory: " + this.availableMemory + " byte (spilled when exeeding " + this.maxMemoryUsage + " byte)\r\n\tmemoryCheckFrequency: " + this.memoryCheckFrequency + "\r\n\tcleanTemp: " + this.cleanTemp + "\r\n\tnumUnaryINDs: " + this.numUnaryINDs + "\r\nstatisticTime: " + this.statisticTime + "\r\nloadTime: " + this.loadTime + "\r\ncompareTime: " + this.compareTime + "\r\noutputTime: " + this.outputTime;
    }

    protected String getAuthorName() {
        return "Thorsten Papenbrock";
    }

    protected String getDescriptionText() {
        return "Revisd Approx Sort-Merge-Join-based IND discovery";
    }

    public void execute() throws AlgorithmExecutionException {
        this.tempFolder = new File(this.tempFolderPath + File.separator + "temp");
        FileUtils.cleanDirectory(this.tempFolder);
        boolean var8 = false;

        try {
            var8 = true;
            this.statisticTime = System.currentTimeMillis();
            this.collectStatistics();
            this.statisticTime = System.currentTimeMillis() - this.statisticTime;
            this.loadTime = System.currentTimeMillis();
            this.initializeAttributes();
            this.loadTime = System.currentTimeMillis() - this.loadTime;
            this.compareTime = System.currentTimeMillis();
            this.calculateINDs();
            this.compareTime = System.currentTimeMillis() - this.compareTime;
            this.outputTime = System.currentTimeMillis();

            this.result = new ArrayList<>();

            this.output();
            System.out.println("--------result size: " + this.result.size() + " --------");
            this.outputTime = System.currentTimeMillis() - this.outputTime;
            var8 = false;
        } catch (IOException var9) {
            var9.printStackTrace();
            throw new AlgorithmExecutionException(var9.getMessage());
        } finally {
            if (var8) {
                if (this.databaseConnectionGenerator != null) {
                    FileUtils.close(this.databaseConnectionGenerator);
                }

                if (this.attributeId2attributeObject != null) {
                    ObjectIterator var4 = this.attributeId2attributeObject.values().iterator();

                    while(var4.hasNext()) {
                        Attribute spiderAttribute = (Attribute)var4.next();
                        FileUtils.close(spiderAttribute);
                    }
                }

                if (this.cleanTemp) {
                    FileUtils.cleanDirectory(this.tempFolder);
                }

            }
        }

        if (this.databaseConnectionGenerator != null) {
            FileUtils.close(this.databaseConnectionGenerator);
        }

        if (this.attributeId2attributeObject != null) {
            ObjectIterator var1 = this.attributeId2attributeObject.values().iterator();

            while(var1.hasNext()) {
                Attribute spiderAttribute = (Attribute)var1.next();
                FileUtils.close(spiderAttribute);
            }
        }

        if (this.cleanTemp) {
            FileUtils.cleanDirectory(this.tempFolder);
        }

    }

    private void collectStatistics() throws InputGenerationException, AlgorithmConfigurationException {
        if (this.databaseConnectionGenerator == null && this.fileInputGenerator == null) {
            throw new InputGenerationException("No input generator specified!");
        } else {
            this.tableColumnStartIndexes = new int[this.tableNames.length];
            this.columnNames = new ArrayList();
            this.columnTypes = new ArrayList();

            for(int tableIndex = 0; tableIndex < this.tableNames.length; ++tableIndex) {
                this.tableColumnStartIndexes[tableIndex] = this.columnNames.size();
                if (this.databaseConnectionGenerator != null) {
                    this.collectStatisticsFrom(this.databaseConnectionGenerator, tableIndex);
                } else {
                    this.collectStatisticsFrom(this.fileInputGenerator[tableIndex]);
                }
            }
            this.numColumns = this.columnNames.size();

            this.violationMatrix = new int[this.numColumns][this.numColumns];
            for (int i = 0; i < numColumns; i++) violationMatrix[i][i] = -1;
            this.maxViolationNum = new int[numColumns];

            this.availableMemory = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax();
            this.maxMemoryUsage = (long)((float)this.availableMemory * ((float)this.maxMemoryUsagePercentage / 100.0F));
        }
    }

    private void collectStatisticsFrom(DatabaseConnectionGenerator inputGenerator, int tableIndex) throws InputGenerationException, AlgorithmConfigurationException {
        ResultSet resultSet = null;

        try {
            resultSet = inputGenerator.generateResultSetFromSql(this.dao.buildColumnMetaQuery(this.databaseName, this.tableNames[tableIndex]));
            this.dao.extract(this.columnNames, new ArrayList(), this.columnTypes, resultSet);
        } catch (SQLException var12) {
            var12.printStackTrace();
            throw new InputGenerationException(var12.getMessage());
        } finally {
            DatabaseUtils.close(resultSet);

            try {
                inputGenerator.closeAllStatements();
            } catch (SQLException var11) {
                var11.printStackTrace();
            }

        }

    }

    private void collectStatisticsFrom(RelationalInputGenerator inputGenerator) throws InputGenerationException, AlgorithmConfigurationException {
        RelationalInput input = null;

        try {
            input = inputGenerator.generateNewCopy();
            Iterator var3 = input.columnNames().iterator();

            while(var3.hasNext()) {
                String columnName = (String)var3.next();
                this.columnNames.add(columnName);
                this.columnTypes.add("String");
            }
        } finally {
            FileUtils.close(input);
        }

    }

    protected void initializeAttributes() throws AlgorithmExecutionException {
        this.attributeId2attributeObject = new Int2ObjectOpenHashMap(this.numColumns);
        this.attributeObjectQueue = new PriorityQueue(this.numColumns);

        for(int table = 0; table < this.tableNames.length; ++table) {
            int firstAttribute = this.tableColumnStartIndexes[table];
            int lastAttribute = table == this.tableNames.length - 1 ? this.numColumns : this.tableColumnStartIndexes[table + 1];

            for(int attribute = firstAttribute; attribute < lastAttribute; ++attribute) {
                Attribute spiderAttribute;
                ColumnSize colSize = new ColumnSize(0);
                if (this.databaseConnectionGenerator != null) {
                    spiderAttribute = new Attribute(attribute, this.columnTypes, this.databaseConnectionGenerator, this.inputRowLimit, this.dao, this.tableNames[table], (String)this.columnNames.get(attribute), this.tempFolder);
                } else {

                    spiderAttribute = new Attribute(attribute, this.columnTypes, this.fileInputGenerator[table], this.inputRowLimit, attribute - firstAttribute, this.tempFolder, this.maxMemoryUsage, this.memoryCheckFrequency, colSize);

                }

                this.attributeId2attributeObject.put(attribute, spiderAttribute);
                this.maxViolationNum[attribute] =(int) ((float)colSize.getColsize() * (float) this.violationRate / denominator);
                if (!spiderAttribute.hasFinished()) {
                    this.attributeObjectQueue.add(spiderAttribute);
                }
            }
        }
        System.out.println("violationRate :  "+this.violationRate);

    }

    protected void calculateINDs() throws IOException {
        IntArrayList topAttributes = new IntArrayList(this.numColumns);

        while(!this.attributeObjectQueue.isEmpty()) {
            Attribute topAttribute = (Attribute)this.attributeObjectQueue.remove();
            topAttributes.add(topAttribute.getAttributeId());

            while(!this.attributeObjectQueue.isEmpty() && topAttribute.getCurrentValue().equals(((Attribute)this.attributeObjectQueue.peek()).getCurrentValue())) {
                topAttributes.add(((Attribute)this.attributeObjectQueue.remove()).getAttributeId());
            }

            IntListIterator var3 = topAttributes.iterator();

            int attribute;
            while(var3.hasNext()) {
                attribute = (Integer)var3.next();
                ((Attribute)this.attributeId2attributeObject.get(attribute)).intersectReferenced(topAttributes, this.attributeId2attributeObject);
            }

            var3 = topAttributes.iterator();

            while(var3.hasNext()) {
                attribute = (Integer)var3.next();
                Attribute spiderAttribute = (Attribute)this.attributeId2attributeObject.get(attribute);
                spiderAttribute.nextValue();
                if (!spiderAttribute.hasFinished()) {
                    this.attributeObjectQueue.add(spiderAttribute);
                }
            }

            topAttributes.clear();
        }

    }


    private void output() throws CouldNotReceiveResultException, ColumnNameMismatchException {
        Int2ObjectOpenHashMap<IntList> dep2ref = new Int2ObjectOpenHashMap(this.numColumns);
        ObjectIterator var2 = this.attributeId2attributeObject.values().iterator();

        while(var2.hasNext()) {
            Attribute spiderAttribute = (Attribute)var2.next();
            if (!spiderAttribute.getReferenced().isEmpty()) {
                dep2ref.put(spiderAttribute.getAttributeId(), new IntArrayList(spiderAttribute.getReferenced()));
            }
        }

        IntIterator var10 = dep2ref.keySet().iterator();

        while(var10.hasNext()) {
            int dep = (Integer)var10.next();
            String depTableName = this.getTableNameFor(dep, this.tableColumnStartIndexes);
            String depColumnName = (String)this.columnNames.get(dep);

            for(IntListIterator var6 = ((IntList)dep2ref.get(dep)).iterator(); var6.hasNext(); ++this.numUnaryINDs) {
                int ref = (Integer)var6.next();
                String refTableName = this.getTableNameFor(ref, this.tableColumnStartIndexes);
                String refColumnName = (String)this.columnNames.get(ref);
                this.result.add(new InclusionDependency(new ColumnPermutation(new ColumnIdentifier[]{new ColumnIdentifier(depTableName, depColumnName)}), new ColumnPermutation(new ColumnIdentifier[]{new ColumnIdentifier(refTableName, refColumnName)})));
                this.resultReceiver.receiveResult(new InclusionDependency(new ColumnPermutation(new ColumnIdentifier[]{new ColumnIdentifier(depTableName, depColumnName)}), new ColumnPermutation(new ColumnIdentifier[]{new ColumnIdentifier(refTableName, refColumnName)})));
            }
        }

    }

    private String getTableNameFor(int column, int[] tableColumnStartIndexes) {
        for(int i = 1; i < tableColumnStartIndexes.length; ++i) {
            if (tableColumnStartIndexes[i] > column) {
                return this.tableNames[i - 1];
            }
        }

        return this.tableNames[this.tableNames.length - 1];
    }
}

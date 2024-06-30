//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package de.metanome.util;

import de.metanome.algorithm_integration.AlgorithmExecutionException;
import de.metanome.algorithm_integration.input.DatabaseConnectionGenerator;
import de.metanome.algorithm_integration.input.InputGenerationException;
import de.metanome.algorithm_integration.input.RelationalInput;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import de.metanome.algorithm_integration.input.TableInputGenerator;
import java.sql.ResultSet;
import java.util.function.Consumer;

public class AttributeHelper {
    public AttributeHelper() {
    }

    public boolean getValues(RelationalInputGenerator input, String table, String column, int inputRowLimit, Consumer<String> consumer) throws AlgorithmExecutionException {
        //return input instanceof TableInputGenerator ? this.getValuesFromDatabase((TableInputGenerator)input, table, column, inputRowLimit, consumer) : this.getValuesFromRelationalInput(input, column, inputRowLimit, consumer);
        return this.getValuesFromRelationalInput(input, column, inputRowLimit, consumer);
    }

    private boolean getValuesFromRelationalInput(RelationalInputGenerator inputGenerator, String column, int inputRowLimit, Consumer<String> consumer) throws AlgorithmExecutionException {
        int rowCount = 0;
        boolean hasValue = false;

        try {
            RelationalInput input = inputGenerator.generateNewCopy();
            Throwable var8 = null;

            boolean var23;
            try {
                int offset = input.columnNames().indexOf(column);

                while(input.hasNext() && (inputRowLimit <= 0 || rowCount < inputRowLimit)) {
                    ++rowCount;
                    String value = (String)input.next().get(offset);
                    if (value != null) {
                        hasValue = true;
                        consumer.accept(value);
                    }
                }

                var23 = hasValue;
            } catch (Throwable var20) {
                var8 = var20;
                throw var20;
            } finally {
                if (input != null) {
                    if (var8 != null) {
                        try {
                            input.close();
                        } catch (Throwable var19) {
                            var8.addSuppressed(var19);
                        }
                    } else {
                        input.close();
                    }
                }

            }

            return var23;
        } catch (Exception var22) {
            throw new InputGenerationException("reading attribute values", var22);
        }
    }

    //只在 getValues 使用，而getValues 在 DeMarchiAlgorithm 253行被调用
    //因此考虑在getValues写死
//    private boolean getValuesFromDatabase(TableInputGenerator input, String table, String column, int inputRowLimit, Consumer<String> consumer) throws AlgorithmExecutionException {
//        String sql = this.buildValueQuery(table, column, inputRowLimit);
//
//        try {
//            DatabaseConnectionGenerator connection = input.getDatabaseConnectionGenerator();
//            Throwable var8 = null;
//
//            try {
//                ResultSet set = connection.generateResultSetFromSql(sql);
//                Throwable var10 = null;
//
//                try {
//                    boolean hasValue = false;
//
//                    while(set.next()) {
//                        hasValue = true;
//                        String value = set.getString(1);
//                        consumer.accept(value);
//                    }
//
//                    boolean var42 = hasValue;
//                    return var42;
//                } catch (Throwable var37) {
//                    var10 = var37;
//                    throw var37;
//                } finally {
//                    if (set != null) {
//                        if (var10 != null) {
//                            try {
//                                set.close();
//                            } catch (Throwable var36) {
//                                var10.addSuppressed(var36);
//                            }
//                        } else {
//                            set.close();
//                        }
//                    }
//
//                }
//            } catch (Throwable var39) {
//                var8 = var39;
//                throw var39;
//            } finally {
//                if (connection != null) {
//                    if (var8 != null) {
//                        try {
//                            connection.close();
//                        } catch (Throwable var35) {
//                            var8.addSuppressed(var35);
//                        }
//                    } else {
//                        connection.close();
//                    }
//                }
//
//            }
//        } catch (Exception var41) {
//            throw new AlgorithmExecutionException("getting attribute values failed", var41);
//        }
//    }

    private String buildValueQuery(String table, String column, int inputRowLimit) {
        StringBuffer buffer = new StringBuffer(50);
        buffer.append(String.format("WITH r AS (SELECT %s FROM %s ", column, table));
        if (inputRowLimit > 0) {
            buffer.append(String.format("LIMIT %d ", inputRowLimit));
        }

        buffer.append(") ");
        buffer.append(String.format("SELECT DISTINCT %s COLLATE \"C\" FROM r WHERE %s IS NOT NULL", column, column));
        return buffer.toString();
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package de.metanome.algorithms.aspider;

import de.metanome.algorithm_integration.AlgorithmConfigurationException;
import de.metanome.algorithm_integration.AlgorithmExecutionException;
import de.metanome.algorithm_integration.algorithm_types.BooleanParameterAlgorithm;
import de.metanome.algorithm_integration.algorithm_types.InclusionDependencyAlgorithm;
import de.metanome.algorithm_integration.algorithm_types.IntegerParameterAlgorithm;
import de.metanome.algorithm_integration.algorithm_types.RelationalInputParameterAlgorithm;
import de.metanome.algorithm_integration.algorithm_types.StringParameterAlgorithm;
import de.metanome.algorithm_integration.configuration.ConfigurationRequirement;
import de.metanome.algorithm_integration.configuration.ConfigurationRequirementBoolean;
import de.metanome.algorithm_integration.configuration.ConfigurationRequirementInteger;
import de.metanome.algorithm_integration.configuration.ConfigurationRequirementRelationalInput;
import de.metanome.algorithm_integration.configuration.ConfigurationRequirementString;
import de.metanome.algorithm_integration.input.InputGenerationException;
import de.metanome.algorithm_integration.input.RelationalInput;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import de.metanome.algorithm_integration.result_receiver.InclusionDependencyResultReceiver;
import de.metanome.algorithms.aspider.core.ASPIDER;
import de.uni_potsdam.hpi.utils.CollectionUtils;
import de.uni_potsdam.hpi.utils.FileUtils;
import java.io.File;
import java.util.ArrayList;

public class ASPIDERFile extends ASPIDER implements InclusionDependencyAlgorithm, RelationalInputParameterAlgorithm, IntegerParameterAlgorithm, StringParameterAlgorithm, BooleanParameterAlgorithm {
    public ASPIDERFile() {
    }

    public ArrayList<ConfigurationRequirement<?>> getConfigurationRequirements() {
        ArrayList<ConfigurationRequirement<?>> configs = new ArrayList(5);
        configs.add(new ConfigurationRequirementRelationalInput(ASPIDERFile.Identifier.INPUT_FILES.name(), -1));
        ConfigurationRequirementString tempFolder = new ConfigurationRequirementString(ASPIDERFile.Identifier.TEMP_FOLDER_PATH.name());
        String[] defaultTempFolder = new String[]{this.tempFolderPath};
        tempFolder.setDefaultValues(defaultTempFolder);
        tempFolder.setRequired(true);
        configs.add(tempFolder);
        ConfigurationRequirementInteger inputRowLimit = new ConfigurationRequirementInteger(ASPIDERFile.Identifier.INPUT_ROW_LIMIT.name());
        Integer[] defaultInputRowLimit = new Integer[]{this.inputRowLimit};
        inputRowLimit.setDefaultValues(defaultInputRowLimit);
        inputRowLimit.setRequired(false);
        configs.add(inputRowLimit);
        ConfigurationRequirementInteger memoryCheckFrequency = new ConfigurationRequirementInteger(ASPIDERFile.Identifier.MEMORY_CHECK_FREQUENCY.name());
        Integer[] defaultMemoryCheckFrequency = new Integer[]{this.memoryCheckFrequency};
        memoryCheckFrequency.setDefaultValues(defaultMemoryCheckFrequency);
        memoryCheckFrequency.setRequired(true);
        configs.add(memoryCheckFrequency);

        ConfigurationRequirementInteger maxMemoryUsagePercentage = new ConfigurationRequirementInteger(ASPIDERFile.Identifier.MAX_MEMORY_USAGE_PERCENTAGE.name());
        Integer[] defaultMaxMemoryUsagePercentage = new Integer[]{this.maxMemoryUsagePercentage};
        maxMemoryUsagePercentage.setDefaultValues(defaultMaxMemoryUsagePercentage);
        maxMemoryUsagePercentage.setRequired(true);
        configs.add(maxMemoryUsagePercentage);

        ConfigurationRequirementInteger violation = new ConfigurationRequirementInteger(ASPIDERFile.Identifier.VIOLATIONRATE_X_10000.name());
        Integer[] violationRate = new Integer[]{this.violationRate};
        violation.setDefaultValues(violationRate);
        violation.setRequired(true);
        configs.add(violation);

        ConfigurationRequirementBoolean cleanTemp = new ConfigurationRequirementBoolean(ASPIDERFile.Identifier.CLEAN_TEMP.name());
        Boolean[] defaultCleanTemp = new Boolean[]{this.cleanTemp};
        cleanTemp.setDefaultValues(defaultCleanTemp);
        cleanTemp.setRequired(true);
        configs.add(cleanTemp);
        return configs;
    }

    public void setRelationalInputConfigurationValue(String identifier, RelationalInputGenerator... values) throws AlgorithmConfigurationException {
        if (ASPIDERFile.Identifier.INPUT_FILES.name().equals(identifier)) {
            this.fileInputGenerator = values;
            this.tableNames = new String[values.length];
            RelationalInput input = null;

            for(int i = 0; i < values.length; ++i) {
                try {
                    input = values[i].generateNewCopy();
                    this.tableNames[i] = input.relationName();
                } catch (InputGenerationException var9) {
                    throw new AlgorithmConfigurationException(var9.getMessage());
                } finally {
                    FileUtils.close(input);
                }
            }
        } else {
            this.handleUnknownConfiguration(identifier, CollectionUtils.concat(values, ","));
        }

    }

    public void setResultReceiver(InclusionDependencyResultReceiver resultReceiver) {
        this.resultReceiver = resultReceiver;
    }

    public void setIntegerConfigurationValue(String identifier, Integer... values) throws AlgorithmConfigurationException {
        if (ASPIDERFile.Identifier.INPUT_ROW_LIMIT.name().equals(identifier)) {
            if (values.length > 0) {
                this.inputRowLimit = values[0];
            }
        } else if (ASPIDERFile.Identifier.MEMORY_CHECK_FREQUENCY.name().equals(identifier)) {
            if (values[0] <= 0) {
                throw new AlgorithmConfigurationException(ASPIDERFile.Identifier.MEMORY_CHECK_FREQUENCY.name() + " must be greater than 0!");
            }

            this.memoryCheckFrequency = values[0];
        }else if (ASPIDERFile.Identifier.VIOLATIONRATE_X_10000.name().equals(identifier)) {
            if (values[0] < 0) {
                throw new AlgorithmConfigurationException(ASPIDERFile.Identifier.VIOLATIONRATE_X_10000.name() + " must be greater than 0!");
            }

            this.violationRate = values[0];
        }
        else if (ASPIDERFile.Identifier.MAX_MEMORY_USAGE_PERCENTAGE.name().equals(identifier)) {
            if (values[0] <= 0) {
                throw new AlgorithmConfigurationException(ASPIDERFile.Identifier.MAX_MEMORY_USAGE_PERCENTAGE.name() + " must be greater than 0!");
            }

            this.maxMemoryUsagePercentage = values[0];
        } else {
            this.handleUnknownConfiguration(identifier, CollectionUtils.concat(values, ","));
        }

    }

    public void setStringConfigurationValue(String identifier, String... values) throws AlgorithmConfigurationException {
        if (ASPIDERFile.Identifier.TEMP_FOLDER_PATH.name().equals(identifier)) {
            if ("".equals(values[0]) || " ".equals(values[0]) || "/".equals(values[0]) || "\\".equals(values[0]) || File.separator.equals(values[0]) || FileUtils.isRoot(new File(values[0]))) {
                throw new AlgorithmConfigurationException(ASPIDERFile.Identifier.TEMP_FOLDER_PATH + " must not be \"" + values[0] + "\"");
            }

            this.tempFolderPath = values[0];
        } else {
            this.handleUnknownConfiguration(identifier, CollectionUtils.concat(values, ","));
        }

    }

    public void setBooleanConfigurationValue(String identifier, Boolean... values) throws AlgorithmConfigurationException {
        if (ASPIDERFile.Identifier.CLEAN_TEMP.name().equals(identifier)) {
            this.cleanTemp = values[0];
        } else {
            this.handleUnknownConfiguration(identifier, CollectionUtils.concat(values, ","));
        }

    }

    protected void handleUnknownConfiguration(String identifier, String value) throws AlgorithmConfigurationException {
        throw new AlgorithmConfigurationException("Unknown configuration: " + identifier + " -> " + value);
    }

    public void execute() throws AlgorithmExecutionException {
        super.execute();
    }

    public String getAuthors() {
        return this.getAuthorName();
    }

    public String getDescription() {
        return this.getDescriptionText();
    }

    public static enum Identifier {
        INPUT_FILES,
        INPUT_ROW_LIMIT,
        TEMP_FOLDER_PATH,
        CLEAN_TEMP,
        MEMORY_CHECK_FREQUENCY,
        MAX_MEMORY_USAGE_PERCENTAGE,
        VIOLATIONRATE_X_10000
        ;

        private Identifier() {
        }
    }

    public static enum Database {
        MYSQL,
        DB2,
        POSTGRESQL,
        FILE;

        private Database() {
        }
    }
}

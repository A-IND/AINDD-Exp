//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package de.metanome.algorithms.ademarchi;

import com.google.common.base.Joiner;
import de.metanome.algorithm_integration.AlgorithmConfigurationException;
import de.metanome.algorithm_integration.AlgorithmExecutionException;
import de.metanome.algorithm_integration.algorithm_types.BooleanParameterAlgorithm;
import de.metanome.algorithm_integration.algorithm_types.InclusionDependencyAlgorithm;
import de.metanome.algorithm_integration.algorithm_types.IntegerParameterAlgorithm;
import de.metanome.algorithm_integration.algorithm_types.RelationalInputParameterAlgorithm;
import de.metanome.algorithm_integration.configuration.ConfigurationRequirement;
import de.metanome.algorithm_integration.configuration.ConfigurationRequirementBoolean;
import de.metanome.algorithm_integration.configuration.ConfigurationRequirementInteger;
import de.metanome.algorithm_integration.configuration.ConfigurationRequirementRelationalInput;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import de.metanome.algorithm_integration.result_receiver.InclusionDependencyResultReceiver;
import de.metanome.algorithms.ademarchi.config.Configuration;
import de.metanome.algorithms.ademarchi.config.ConfigurationKey;
import java.util.ArrayList;
import java.util.Arrays;

public class ADeMarchiAlgorithm extends ADeMarchi implements InclusionDependencyAlgorithm, RelationalInputParameterAlgorithm, BooleanParameterAlgorithm, IntegerParameterAlgorithm {
  private final Configuration defaultValues = Configuration.withDefaults();
  private final Configuration.ConfigurationBuilder builder = Configuration.builder();

  public ADeMarchiAlgorithm() {
  }

  public ArrayList<ConfigurationRequirement<?>> getConfigurationRequirements() {
    ArrayList<ConfigurationRequirement<?>> configs = new ArrayList(4);
    configs.add(new ConfigurationRequirementRelationalInput(ConfigurationKey.TABLE.name(), -1));
    ConfigurationRequirementInteger inputRowLimit = new ConfigurationRequirementInteger(ConfigurationKey.INPUT_ROW_LIMIT.name());
    Integer[] defaultInputRowLimit = new Integer[]{this.inputRowLimit};
    inputRowLimit.setDefaultValues(defaultInputRowLimit);
    inputRowLimit.setRequired(true);
    configs.add(inputRowLimit);
    ConfigurationRequirementInteger violationRate = new ConfigurationRequirementInteger(ConfigurationKey.VIOLATION_RATE.name());
    Integer[] violateRate = new Integer[]{this.violationRate};
    violationRate.setDefaultValues(violateRate);
    violationRate.setRequired(true);
    configs.add(violationRate);
    ConfigurationRequirementBoolean processEmpty = new ConfigurationRequirementBoolean(ConfigurationKey.PROCESS_EMPTY_COLUMNS.name());
    Boolean[] defaultCleanTemp = new Boolean[]{this.processEmpty};
    processEmpty.setDefaultValues(defaultCleanTemp);
    processEmpty.setRequired(true);
    configs.add(processEmpty);
    return configs;
  }

  public void setResultReceiver(InclusionDependencyResultReceiver resultReceiver) {
    this.resultReceiver = resultReceiver;
  }

  public void setRelationalInputConfigurationValue(String identifier, RelationalInputGenerator... values) throws AlgorithmConfigurationException {
    if (identifier.equals(ConfigurationKey.TABLE.name())) {
      this.builder.relationalInputGenerators(Arrays.asList(values));
    } else {
      this.handleUnknownConfiguration(identifier, values);
    }

  }

  public void setBooleanConfigurationValue(String identifier, Boolean... values) throws AlgorithmConfigurationException {
    if (identifier.equals(ConfigurationKey.PROCESS_EMPTY_COLUMNS.name())) {
      this.processEmpty = values[0];
    } else {
      this.handleUnknownConfiguration(identifier, values);
    }

  }

  public void setIntegerConfigurationValue(String identifier, Integer... values) throws AlgorithmConfigurationException {
    if (identifier.equals(ConfigurationKey.INPUT_ROW_LIMIT.name())) {
      this.inputRowLimit = values[0];
    } else if (identifier.equals(ConfigurationKey.VIOLATION_RATE.name())) {
      this.violationRate = values[0];
    } else {
      this.handleUnknownConfiguration(identifier, values);
    }

  }

  @SafeVarargs
  private final <T> void handleUnknownConfiguration(String identifier, T... values) throws AlgorithmConfigurationException {
    String formattedValues = Joiner.on(", ").join(values);
    String message = String.format("unknown configuration '%s', values: '%s'", identifier, formattedValues);
    throw new AlgorithmConfigurationException(message);
  }

  public void execute() {
    try {
      Configuration configuration = this.builder.build();
      super.execute(configuration);
    } catch (AlgorithmExecutionException var2) {
      throw new RuntimeException(var2);
    }
  }

  public String getAuthors() {
    return "Wang and Su";
  }

  public String getDescription() {
    return "Revised DeMarchi Algorithm";
  }
}

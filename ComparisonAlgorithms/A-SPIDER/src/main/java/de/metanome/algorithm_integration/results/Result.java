package de.metanome.algorithm_integration.results;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.metanome.algorithm_integration.result_receiver.ColumnNameMismatchException;
import de.metanome.algorithm_integration.result_receiver.CouldNotReceiveResultException;
import de.metanome.algorithm_integration.result_receiver.OmniscientResultReceiver;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlTransient;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@Type(value = BasicStatistic.class, name = "BasicStatistic"), @Type(value = ConditionalUniqueColumnCombination.class, name = "ConditionalUniqueColumnCombination"), @Type(value = FunctionalDependency.class, name = "FunctionalDependency"), @Type(value = ConditionalInclusionDependency.class, name = "ConditionalInclusionDependency"), @Type(value = InclusionDependency.class, name = "InclusionDependency"), @Type(value = OrderDependency.class, name = "OrderDependency"), @Type(value = UniqueColumnCombination.class, name = "UniqueColumnCombination"), @Type(value = MultivaluedDependency.class, name = "MultivaluedDependency"), @Type(value = DenialConstraint.class, name = "DenialConstraint")})
public interface Result extends Serializable {
  @XmlTransient
  void sendResultTo(OmniscientResultReceiver paramOmniscientResultReceiver) throws CouldNotReceiveResultException, ColumnNameMismatchException;
}


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\results\Result.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
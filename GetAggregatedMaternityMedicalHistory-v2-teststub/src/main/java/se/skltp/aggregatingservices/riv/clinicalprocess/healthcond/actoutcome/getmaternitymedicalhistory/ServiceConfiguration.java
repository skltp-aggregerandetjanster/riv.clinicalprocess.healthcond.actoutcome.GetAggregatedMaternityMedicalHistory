package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistory;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistory.v2.rivtabp21.GetMaternityMedicalHistoryResponderInterface;
import riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistory.v2.rivtabp21.GetMaternityMedicalHistoryResponderService;
import se.skltp.aggregatingservices.config.TestProducerConfiguration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "getaggregatedmaternitymedicalhistory.v2.teststub")
public class ServiceConfiguration extends TestProducerConfiguration {

  public static final String SCHEMA_PATH =
      "/schemas/clinicalprocess_healthcond_actoutcome_2.0_RC13/interactions/GetMaternityMedicalHistoryInteraction/GetMaternityMedicalHistoryInteraction_2.0_RIVTABP21.wsdl";

  public ServiceConfiguration() {
    setProducerAddress("http://localhost:8083/vp");
    setServiceClass(GetMaternityMedicalHistoryResponderInterface.class.getName());
    setServiceNamespace("urn:riv:clinicalprocess:healthcond:actoutcome:GetMaternityMedicalHistoryResponder:2");
    setPortName(GetMaternityMedicalHistoryResponderService.GetMaternityMedicalHistoryResponderPort.toString());
    setWsdlPath(SCHEMA_PATH);
    setTestDataGeneratorClass(ServiceTestDataGenerator.class.getName());
    setServiceTimeout(27000);
  }
}

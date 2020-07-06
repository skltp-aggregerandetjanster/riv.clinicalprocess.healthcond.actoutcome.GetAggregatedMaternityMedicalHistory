package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistory;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistory.v2.rivtabp21.GetMaternityMedicalHistoryResponderInterface;
import riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistory.v2.rivtabp21.GetMaternityMedicalHistoryResponderService;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "getaggregatedmaternitymedicalhistory.v2")
public class GAMMHAgpServiceConfiguration extends se.skltp.aggregatingservices.configuration.AgpServiceConfiguration {

public static final String SCHEMA_PATH = "/schemas/clinicalprocess_healthcond_actoutcome_2.0_RC13/interactions/GetMaternityMedicalHistoryInteraction/GetMaternityMedicalHistoryInteraction_2.0_RIVTABP21.wsdl";

  public GAMMHAgpServiceConfiguration() {

    setServiceName("GetAggregatedMaternityMedicalHistory-v2");
    setTargetNamespace("urn:riv:clinicalprocess:healthcond:actoutcome:GetMaternityMedicalHistory:2:rivtabp21");

    // Set inbound defaults
    setInboundServiceURL("http://localhost:9003/GetAggregatedMaternityMedicalHistory/service/v2");
    setInboundServiceWsdl(SCHEMA_PATH);
    setInboundServiceClass(GetMaternityMedicalHistoryResponderInterface.class.getName());
    setInboundPortName(GetMaternityMedicalHistoryResponderService.GetMaternityMedicalHistoryResponderPort.toString());

    // Set outbound defaults
    setOutboundServiceWsdl(SCHEMA_PATH);
    setOutboundServiceClass(GetMaternityMedicalHistoryResponderInterface.class.getName());
    setOutboundPortName(GetMaternityMedicalHistoryResponderService.GetMaternityMedicalHistoryResponderPort.toString());

    // FindContent
    setEiServiceDomain("riv:clinicalprocess:healthcond:actoutcome");
    setEiCategorization("utr-mtr");

    // TAK
    setTakContract("urn:riv:clinicalprocess:healthcond:actoutcome:GetMaternityMedicalHistoryResponder:2");

    // Set service factory
    setServiceFactoryClass(GAMMHAgpServiceFactoryImpl.class.getName());
    }


}

package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistory;


import riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistoryresponder.v2.GetMaternityMedicalHistoryResponseType;
import se.skltp.aggregatingservices.api.AgpServiceFactory;
import se.skltp.aggregatingservices.tests.CreateAggregatedResponseTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class GAMMHCreateAggregatedResponseTest extends CreateAggregatedResponseTest {

  private static GAMMHAgpServiceConfiguration configuration = new GAMMHAgpServiceConfiguration();
  private static AgpServiceFactory<GetMaternityMedicalHistoryResponseType> agpServiceFactory =
      new GAMMHAgpServiceFactoryImpl();
  private static ServiceTestDataGenerator testDataGenerator = new ServiceTestDataGenerator();

  public GAMMHCreateAggregatedResponseTest() {
    super(testDataGenerator, agpServiceFactory, configuration);
  }

  @Override
  public int getResponseSize(Object response) {
    GetMaternityMedicalHistoryResponseType responseType =
        (GetMaternityMedicalHistoryResponseType) response;
    return responseType.getMaternityMedicalRecord().size();
  }
}

package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistory;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistoryresponder.v2.GetMaternityMedicalHistoryResponseType;
import se.skltp.aggregatingservices.api.AgpServiceFactory;
import se.skltp.aggregatingservices.tests.CreateAggregatedResponseTest;

@RunWith(SpringJUnit4ClassRunner.class)
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

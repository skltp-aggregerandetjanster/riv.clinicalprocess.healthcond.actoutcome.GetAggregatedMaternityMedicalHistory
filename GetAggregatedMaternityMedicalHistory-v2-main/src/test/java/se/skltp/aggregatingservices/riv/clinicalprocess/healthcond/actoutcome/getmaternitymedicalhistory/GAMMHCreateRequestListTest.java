package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistory;

import riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistoryresponder.v2.GetMaternityMedicalHistoryResponseType;
import se.skltp.aggregatingservices.api.AgpServiceFactory;
import se.skltp.aggregatingservices.tests.CreateRequestListTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class GAMMHCreateRequestListTest extends CreateRequestListTest {

  private static GAMMHAgpServiceConfiguration configuration = new GAMMHAgpServiceConfiguration();
  private static AgpServiceFactory<GetMaternityMedicalHistoryResponseType> agpServiceFactory =
      new GAMMHAgpServiceFactoryImpl();
  private static ServiceTestDataGenerator testDataGenerator = new ServiceTestDataGenerator();

  public GAMMHCreateRequestListTest() {
    super(testDataGenerator, agpServiceFactory, configuration);
  }
}

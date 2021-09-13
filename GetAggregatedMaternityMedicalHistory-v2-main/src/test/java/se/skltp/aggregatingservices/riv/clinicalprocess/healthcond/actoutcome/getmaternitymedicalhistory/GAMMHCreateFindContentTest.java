package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistory;

import riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistoryresponder.v2.GetMaternityMedicalHistoryResponseType;
import se.skltp.aggregatingservices.api.AgpServiceFactory;
import se.skltp.aggregatingservices.tests.CreateFindContentTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class GAMMHCreateFindContentTest extends CreateFindContentTest {

  private static GAMMHAgpServiceConfiguration configuration = new GAMMHAgpServiceConfiguration();
  private static AgpServiceFactory<GetMaternityMedicalHistoryResponseType> agpServiceFactory = new GAMMHAgpServiceFactoryImpl();
  private static ServiceTestDataGenerator testDataGenerator = new ServiceTestDataGenerator();

  public GAMMHCreateFindContentTest() {
    super(testDataGenerator, agpServiceFactory, configuration);
  }
}

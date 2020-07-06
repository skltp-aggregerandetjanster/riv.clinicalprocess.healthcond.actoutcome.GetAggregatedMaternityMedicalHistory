package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistory;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import riv.clinicalprocess.healthcond.actoutcome.getmaternitymedicalhistoryresponder.v2.GetMaternityMedicalHistoryResponseType;
import se.skltp.aggregatingservices.api.AgpServiceFactory;
import se.skltp.aggregatingservices.tests.CreateFindContentTest;
import se.skltp.aggregatingservices.data.TestDataGenerator;


@RunWith(SpringJUnit4ClassRunner.class)
public class GAMMHCreateFindContentTest extends CreateFindContentTest {

  private static GAMMHAgpServiceConfiguration configuration = new GAMMHAgpServiceConfiguration();
  private static AgpServiceFactory<GetMaternityMedicalHistoryResponseType> agpServiceFactory = new GAMMHAgpServiceFactoryImpl();
  private static ServiceTestDataGenerator testDataGenerator = new ServiceTestDataGenerator();

  public GAMMHCreateFindContentTest() {
    super(testDataGenerator, agpServiceFactory, configuration);
  }

  @BeforeClass
  public static void before() {
    configuration = new GAMMHAgpServiceConfiguration();
    agpServiceFactory = new GAMMHAgpServiceFactoryImpl();
    agpServiceFactory.setAgpServiceConfiguration(configuration);
  }


}

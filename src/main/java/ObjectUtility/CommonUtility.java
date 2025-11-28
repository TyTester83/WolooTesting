package ObjectUtility;

import adminDashboardRepo.AddNewFacilityPage;
import adminDashboardRepo.AdminDashboardPage;
import adminDashboardRepo.AdminProfilePage;
import adminDashboardRepo.AssignNewTaskPopup;
import adminDashboardRepo.ChooseExistingTaskbuddyPopup;
import adminDashboardRepo.FacilityRenewPopup;
import adminDashboardRepo.NewUserRenewPopup;
import adminDashboardRepo.PaynowPage;
import adminLoginRepo.AdminAssignTaskPage;
import adminLoginRepo.AssignSupervisorPage;
import adminLoginRepo.AssignTaskBuddyPage;
import adminLoginRepo.ChooseAdminPage;
import adminLoginRepo.CongratulationPopup;
import adminLoginRepo.ExplorePage;
import adminLoginRepo.GetStartedPage;
import adminLoginRepo.ListYourFacilityPage;
import adminLoginRepo.ScheduleTaskTimingsPage;
import adminLoginRepo.ShiftTimePage;
import adminLoginRepo.TaskTimingConfirmPopup;
import appWelcomeRepo.AppWelcomePage;
import appWelcomeRepo.LoginPage;
import appWelcomeRepo.OTPPage;
import facilityUpgradeRepo.RenewMembershipPopup;
import facilityUpgradeRepo.UpgradeToPremiumPopup;
import facilityUpgradeRepo.UpgradetoPremiumPage;
import io.appium.java_client.AppiumDriver;
import iotDashboardRepo.IOTDashboardPage;
import iotOnboardingRepo.StinqGuardPage;
import razorpayPaymentRepo.ContactDetailsPage;
import razorpayPaymentRepo.NetbankingPage;
import razorpayPaymentRepo.PaymentConfirmationPage;
import razorpayPaymentRepo.RazorpayPage;
import supervisorDashboardRepo.AccountPage;
import supervisorDashboardRepo.ClusterPage;
import supervisorDashboardRepo.CompletedTaskTab;
import supervisorDashboardRepo.JanitorDetailsPage;
import supervisorDashboardRepo.JanitorListPage;
import supervisorDashboardRepo.PendingTaskTab;
import supervisorDashboardRepo.SupervisorDashboardPage;
import supervisorDashboardRepo.SupervisorRejectedTaskTab;
import supervisorDashboardRepo.SupervisorRequestForClosureTab;
import supervisorDashboardRepo.TaskApprovalPage;
import supervisorReassignRepo.JanitorSchedulePage;
import supervisorReassignRepo.SearchJanitorPage;
import supervisorReportIssueRepo.ReportIssuePage;
import taskBuddyDashboardRepo.BuddyAcceptedTaskTab;
import taskBuddyDashboardRepo.BuddyCompletedTaskTab;
import taskBuddyDashboardRepo.BuddyPendingTaskTab;
import taskBuddyDashboardRepo.BuddyProfilePage;
import taskBuddyDashboardRepo.BuddyRejectedTaskTab;
import taskBuddyDashboardRepo.OnGoingTab;
import taskBuddyDashboardRepo.RequestForClosureTab;
import taskBuddyDashboardRepo.SelfiPage;
import taskBuddyDashboardRepo.TaskClosurePopup;
import taskBuddyDashboardRepo.TaskPage;
import taskBuddyDashboardRepo.TaskPhotosPage;
import taskBuddyDashboardRepo.TaskbuddyDashboardPage;

public class CommonUtility {
	private AppiumDriver driver;
	private AppWelcomePage welcomePage;
	private LoginPage loginPage;
	private OTPPage otpPage;
	private ExplorePage explorePage;
	private GetStartedPage getStartPage;
	private ListYourFacilityPage facilityPage;
	private AdminAssignTaskPage taskPage;
	private ShiftTimePage timePage;
	private ScheduleTaskTimingsPage scheduleTaskTimingPage;
	private TaskTimingConfirmPopup taskConfirmPopup;
	private ChooseAdminPage adminPage;
	private AssignSupervisorPage supervisorPage;
	private AssignTaskBuddyPage buddyPage;
	private CongratulationPopup congrats;
	private AdminDashboardPage adminDashboard;
	private AdminProfilePage profilePage;
	private SupervisorDashboardPage supervisorDashboardPage;
	private PendingTaskTab pendingTaskTab;
	private AccountPage accountPage;
	private TaskbuddyDashboardPage buddyDashboardPage;
	private BuddyProfilePage buddyProfilePage;
	private ClusterPage clusterPage;
	private JanitorListPage janitorListPage;
	private JanitorDetailsPage janitorDetailPage;
	private BuddyPendingTaskTab buddyPendingTaskTab;
	private BuddyAcceptedTaskTab buddyAcceptedTab;
	private SelfiPage selfiPage;
	private TaskPage buddyTaskPage;
	private OnGoingTab buddyOngoingTab;
	private TaskPhotosPage taskPhotoPage;
	private TaskClosurePopup closurePopup;
	private RequestForClosureTab buddyRequestForClosureTab;
	private SupervisorRequestForClosureTab taskClosureTab;
	private TaskApprovalPage approvalPage;
	private CompletedTaskTab completedTaskTab;
	private BuddyCompletedTaskTab buddyCompletedTaskTab;
	private AddNewFacilityPage newFacilitypage;
	private UpgradeToPremiumPopup premiumPopup;
	private AssignNewTaskPopup newTaskPopup;
	private ChooseExistingTaskbuddyPopup existingTaskbuddyPopup;
	private NewUserRenewPopup newUserRenewPopup;
	private PaynowPage paynowPage;
	private RazorpayPage paymentOptionsPage;
	private NetbankingPage netBankPage;
	private PaymentConfirmationPage paymentConfirmPage;
	private IOTDashboardPage iotDashboardPage;
	private FacilityRenewPopup facilityRenewPopup;
	private UpgradetoPremiumPage premiumPage;
	private SupervisorRejectedTaskTab supevisorRejectedTab;
	private JanitorSchedulePage schedulePage;
	private SearchJanitorPage searchJanitorsPage;
	private BuddyRejectedTaskTab buddyRejectedTab;
	private StinqGuardPage stinqguardPage;
	private RenewMembershipPopup renewMembershipPopup;
	private ReportIssuePage issuePage;
	private ContactDetailsPage razorContactPage;

	public CommonUtility(AppiumDriver driver) {
		this.driver = driver;
	}

	// Admin POM Object

	public AppWelcomePage welcomePage() {
		return (welcomePage == null) ? welcomePage = new AppWelcomePage(driver) : welcomePage;
	}

	public LoginPage loginPage() {
		return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
	}

	public OTPPage otpPage() {
		return (otpPage == null) ? otpPage = new OTPPage(driver) : otpPage;
	}

	public ExplorePage explorePage() {
		return (explorePage == null) ? explorePage = new ExplorePage(driver) : explorePage;
	}

	public GetStartedPage getStartPage() {
		return (getStartPage == null) ? getStartPage = new GetStartedPage(driver) : getStartPage;
	}

	public ListYourFacilityPage facilityPage() {
		return (facilityPage == null) ? facilityPage = new ListYourFacilityPage(driver) : facilityPage;
	}

	public AdminAssignTaskPage taskPage() {
		return (taskPage == null) ? taskPage = new AdminAssignTaskPage(driver) : taskPage;
	}

	public ShiftTimePage timePage() {
		return (timePage == null) ? timePage = new ShiftTimePage(driver) : timePage;
	}

	public ScheduleTaskTimingsPage scheduleTaskTimingPage() {
		return (scheduleTaskTimingPage == null) ? scheduleTaskTimingPage = new ScheduleTaskTimingsPage(driver)
				: scheduleTaskTimingPage;
	}

	public TaskTimingConfirmPopup taskConfirmPopup() {
		return (taskConfirmPopup == null) ? taskConfirmPopup = new TaskTimingConfirmPopup(driver) : taskConfirmPopup;
	}

	public ChooseAdminPage adminPage() {
		return (adminPage == null) ? adminPage = new ChooseAdminPage(driver) : adminPage;
	}

	public AssignSupervisorPage supervisorPage() {
		return (supervisorPage == null) ? supervisorPage = new AssignSupervisorPage(driver) : supervisorPage;
	}

	public AssignTaskBuddyPage buddyPage() {
		return (buddyPage == null) ? buddyPage = new AssignTaskBuddyPage(driver) : buddyPage;
	}

	public CongratulationPopup congrats() {
		return (congrats == null) ? congrats = new CongratulationPopup(driver) : congrats;
	}

	public AdminDashboardPage adminDashboard() {
		return (adminDashboard == null) ? adminDashboard = new AdminDashboardPage(driver) : adminDashboard;
	}

	public AdminProfilePage profilePage() {
		return (profilePage == null) ? profilePage = new AdminProfilePage(driver) : profilePage;
	}

	public AddNewFacilityPage newFacilitypage() {
		return (newFacilitypage == null) ? newFacilitypage = new AddNewFacilityPage(driver) : newFacilitypage;
	}

	public UpgradeToPremiumPopup premiumPopup() {
		return (premiumPopup == null) ? premiumPopup = new UpgradeToPremiumPopup(driver) : premiumPopup;
	}

	public AssignNewTaskPopup newTaskPopup() {
		return (newTaskPopup == null) ? newTaskPopup = new AssignNewTaskPopup(driver) : newTaskPopup;
	}

	public ChooseExistingTaskbuddyPopup existingTaskbuddyPopup() {
		return (existingTaskbuddyPopup == null) ? existingTaskbuddyPopup = new ChooseExistingTaskbuddyPopup(driver)
				: existingTaskbuddyPopup;
	}

	public NewUserRenewPopup newUserRenewPopup() {
		return (newUserRenewPopup == null) ? newUserRenewPopup = new NewUserRenewPopup(driver) : newUserRenewPopup;
	}

	public PaynowPage paynowPage() {
		return (paynowPage == null) ? paynowPage = new PaynowPage(driver) : paynowPage;
	}

	public RazorpayPage paymentOptionsPage() {
		return (paymentOptionsPage == null) ? paymentOptionsPage = new RazorpayPage(driver) : paymentOptionsPage;
	}

	public NetbankingPage netBankPage() {
		return (netBankPage == null) ? netBankPage = new NetbankingPage(driver) : netBankPage;
	}

	public PaymentConfirmationPage paymentConfirmPage() {
		return (paymentConfirmPage == null) ? paymentConfirmPage = new PaymentConfirmationPage(driver)
				: paymentConfirmPage;
	}

	public IOTDashboardPage iotDashboardPage() {
		return (iotDashboardPage == null) ? iotDashboardPage = new IOTDashboardPage(driver) : iotDashboardPage;
	}

	public FacilityRenewPopup facilityRenewPopup() {
		return (facilityRenewPopup == null) ? facilityRenewPopup = new FacilityRenewPopup(driver) : facilityRenewPopup;
	}

	public UpgradetoPremiumPage premiumPage() {
		return (premiumPage == null) ? premiumPage = new UpgradetoPremiumPage(driver) : premiumPage;
	}

	public StinqGuardPage stinqguardPage() {
		return (stinqguardPage == null) ? stinqguardPage = new StinqGuardPage(driver) : stinqguardPage;
	}

	public RenewMembershipPopup renewMembershipPopup() {
		return (renewMembershipPopup == null) ? renewMembershipPopup = new RenewMembershipPopup(driver)
				: renewMembershipPopup;
	}

	public ContactDetailsPage razorContactPage() {
		return (razorContactPage == null) ? razorContactPage = new ContactDetailsPage(driver) : razorContactPage;
	}

	// Supervisor POM Object

	public SupervisorDashboardPage supervisorDashboardPage() {
		return (supervisorDashboardPage == null) ? supervisorDashboardPage = new SupervisorDashboardPage(driver)
				: supervisorDashboardPage;
	}

	public PendingTaskTab pendingTaskTab() {
		return (pendingTaskTab == null) ? pendingTaskTab = new PendingTaskTab(driver) : pendingTaskTab;
	}

	public AccountPage accountPage() {
		return (accountPage == null) ? accountPage = new AccountPage(driver) : accountPage;
	}

	public ClusterPage clusterPage() {
		return (clusterPage == null) ? clusterPage = new ClusterPage(driver) : clusterPage;
	}

	public JanitorListPage janitorListPage() {
		return (janitorListPage == null) ? janitorListPage = new JanitorListPage(driver) : janitorListPage;
	}

	public JanitorDetailsPage janitorDetailPage() {
		return (janitorDetailPage == null) ? janitorDetailPage = new JanitorDetailsPage(driver) : janitorDetailPage;
	}

	public SupervisorRequestForClosureTab taskClosureTab() {
		return (taskClosureTab == null) ? taskClosureTab = new SupervisorRequestForClosureTab(driver) : taskClosureTab;
	}

	public TaskApprovalPage approvalPage() {
		return (approvalPage == null) ? approvalPage = new TaskApprovalPage(driver) : approvalPage;
	}

	public CompletedTaskTab completedTaskTab() {
		return (completedTaskTab == null) ? completedTaskTab = new CompletedTaskTab(driver) : completedTaskTab;
	}

	public SupervisorRejectedTaskTab supevisorRejectedTab() {
		return (supevisorRejectedTab == null) ? supevisorRejectedTab = new SupervisorRejectedTaskTab(driver)
				: supevisorRejectedTab;
	}

	public JanitorSchedulePage schedulePage() {
		return (schedulePage == null) ? schedulePage = new JanitorSchedulePage(driver) : schedulePage;
	}

	public SearchJanitorPage searchJanitorsPage() {
		return (searchJanitorsPage == null) ? searchJanitorsPage = new SearchJanitorPage(driver) : searchJanitorsPage;
	}

	public ReportIssuePage issuePage() {
		return (issuePage == null) ? issuePage = new ReportIssuePage(driver) : issuePage;
	}

	// TaskBuddy POM Object

	public TaskbuddyDashboardPage buddyDashboardPage() {
		return (buddyDashboardPage == null) ? buddyDashboardPage = new TaskbuddyDashboardPage(driver)
				: buddyDashboardPage;
	}

	public BuddyProfilePage buddyProfilePage() {
		return (buddyProfilePage == null) ? buddyProfilePage = new BuddyProfilePage(driver) : buddyProfilePage;
	}

	public BuddyPendingTaskTab buddyPendingTaskTab() {
		return (buddyPendingTaskTab == null) ? buddyPendingTaskTab = new BuddyPendingTaskTab(driver)
				: buddyPendingTaskTab;
	}

	public BuddyAcceptedTaskTab buddyAcceptedTab() {
		return (buddyAcceptedTab == null) ? buddyAcceptedTab = new BuddyAcceptedTaskTab(driver) : buddyAcceptedTab;
	}

	public SelfiPage selfiPage() {
		return (selfiPage == null) ? selfiPage = new SelfiPage(driver) : selfiPage;
	}

	public TaskPage buddyTaskPage() {
		return (buddyTaskPage == null) ? buddyTaskPage = new TaskPage(driver) : buddyTaskPage;
	}

	public OnGoingTab buddyOngoingTab() {
		return (buddyOngoingTab == null) ? buddyOngoingTab = new OnGoingTab(driver) : buddyOngoingTab;
	}

	public TaskPhotosPage taskPhotoPage() {
		return (taskPhotoPage == null) ? taskPhotoPage = new TaskPhotosPage(driver) : taskPhotoPage;
	}

	public TaskClosurePopup closurePopup() {
		return (closurePopup == null) ? closurePopup = new TaskClosurePopup(driver) : closurePopup;
	}

	public RequestForClosureTab buddyRequestForClosureTab() {
		return (buddyRequestForClosureTab == null) ? buddyRequestForClosureTab = new RequestForClosureTab(driver)
				: buddyRequestForClosureTab;
	}

	public BuddyCompletedTaskTab buddyCompletedTaskTab() {
		return (buddyCompletedTaskTab == null) ? buddyCompletedTaskTab = new BuddyCompletedTaskTab(driver)
				: buddyCompletedTaskTab;
	}

	public BuddyRejectedTaskTab buddyRejectedTab() {
		return (buddyRejectedTab == null) ? buddyRejectedTab = new BuddyRejectedTaskTab(driver) : buddyRejectedTab;
	}

}

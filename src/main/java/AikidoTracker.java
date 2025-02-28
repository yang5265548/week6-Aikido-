import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;


public class AikidoTracker {


    private ArrayList<TrainingSession> sessions = new ArrayList<>();
    private LocalDate startDate;

    public void setStartDate(String date){
        this.startDate=LocalDate.parse(date);

    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public AikidoTracker() {
        this.startDate = LocalDate.now();  // 第一次启动时，记录开始日期
    }

    // 添加训练记录
    public void addSession(String date, int duration) {
        sessions.add(new TrainingSession(date, duration));
    }

    // 获取总训练次数
    public int getTotalSessions() {
        return sessions.size();
    }

    // 获取总训练时长（分钟）
    public int getTotalPracticeTime() {
        int total = 0;
        for (TrainingSession session : sessions) {
            total += session.getDuration();
        }
        return total;
    }

    // 判断是否符合Kyu晋升资格
    public boolean checkGraduationEligibility() {
        if (getTotalSessions() >= 100) {
            return true;  // 满100次自动合格
        }

        if (sessions.size() > 0) {
            String latestDate = sessions.get(sessions.size() - 1).getDate();
            LocalDate latestSessionDate = LocalDate.parse(latestDate);
            long monthsBetween = ChronoUnit.MONTHS.between(startDate, latestSessionDate);
            if (monthsBetween >= 6) {
                return true;
            }
        }
        return false;
    }

    // 菜单和交互逻辑
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== Aikido Practice Tracker =====");
            System.out.println("1. Add Practice Session");
            System.out.println("2. View Total Sessions and Time");
            System.out.println("3. Check Kyu Graduation Eligibility");
            System.out.println("4. Exit");
            System.out.print("Please choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter practice date (yyyy-MM-dd): ");
                    String date = scanner.nextLine();
                    LocalDate sessionDate = LocalDate.parse(date);

                    // 判断输入日期是否早于系统启动日期（startDate）
                    if (sessionDate.isBefore(startDate)) {
                        System.out.println("Error: Practice date cannot be earlier than the system start date (" + startDate + ").\n");
                    } else {
                        System.out.print("Enter practice duration (minutes): ");
                        int duration = scanner.nextInt();
                        addSession(date, duration);
                        System.out.println("Practice session added!\n");
                    }
                    break;

                case 2:
                    System.out.println("Total practice sessions: " + getTotalSessions());
                    System.out.println("Total practice time: " + getTotalPracticeTime() + " minutes\n");
                    break;
                case 3:
                    if (checkGraduationEligibility()) {
                        System.out.println("Eligible for Kyu graduation!\n");
                    } else {
                        System.out.println("Not yet eligible for Kyu graduation.\n");
                    }
                    break;
                case 4:
                    System.out.println("Exiting the system.");
                    return;
                default:
                    System.out.println("Invalid option, please choose again.\n");
            }
        }
    }



}



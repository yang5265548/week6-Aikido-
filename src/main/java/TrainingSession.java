public class TrainingSession{
    private String date;
    private int session;

    public TrainingSession(String date, int session) {
        this.date = date;
        this.session = session;
    }


    public int getDuration(){
        return session;
    }


    public String getDate(){
        return date;
    }
}
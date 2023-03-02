public interface IView {
    public void  start();
    public void  stop();
     public <T> void say(T message);
    public String get(String prompt);
    public String get();
}

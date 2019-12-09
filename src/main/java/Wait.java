import java.util.concurrent.ExecutorService;

public interface Wait {

    public abstract void wait(ExecutorService service,int seconds);
}

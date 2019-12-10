import java.util.concurrent.ExecutorService;

@FunctionalInterface
public interface Wait {
    void wait(ExecutorService service, int seconds);
}

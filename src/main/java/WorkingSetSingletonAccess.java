import java.lang.IllegalStateException;

public class WorkingSetSingletonAccess {
  private static WorkingSetMaintainer maintainer;

  private WorkingSetSingletonAccess(int numOfPages) {
    maintainer = new WorkingSetMaintainer(numOfPages);
  }

  public static int pageFault() {
    if (maintainer == null)
      throw new IllegalStateException("WorkingSetSingletonAccess must be initialized by calling instance_init (# of virtual pages)");
    return maintainer.removeSomeoneNotFromWorkingSet();
  }

  public static void registerPage(int pageNum) {
    if (maintainer == null)
      throw new IllegalStateException("WorkingSetSingletonAccess must be initialized by calling instance_init (# of virtual pages)");
    maintainer.registerNewPage(pageNum);
  }

  public static void init(int numOfPages) {
    maintainer = new WorkingSetMaintainer(numOfPages);
  }
}

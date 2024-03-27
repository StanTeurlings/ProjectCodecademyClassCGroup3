package Domain.Class;

public class WatchedModule {
    private Module module;

    public WatchedModule(Module module) {
        this.module = module;
    }

    // Getters and setters
    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
    // Assuming there may be additional behaviors associated with a watched module
    // that are not captured in the UML diagram
    // For example, you might want to track the watched date, completion status,
    // etc.
}

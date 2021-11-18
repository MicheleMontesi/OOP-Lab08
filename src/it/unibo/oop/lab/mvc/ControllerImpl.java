package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ControllerImpl implements Controller {

    private String nextString;
    private final List<String> history = new LinkedList<>();
    /**
     * 
     */
    @Override
    public void setNewString(final String nextString) {
        this.nextString = Objects.requireNonNull(nextString);
    }
    /**
     * 
     */
    @Override
    public String getNextString() {
        return this.nextString;
    }
    /**
     * 
     */
    @Override
    public List<String> getHistory() {
        return this.history;
    }
    /**
     * 
     */
    @Override
    public void printString() {
        if (!"".equals(nextString)) {
            System.out.println(nextString);
        } else {
            throw new IllegalStateException();
        }
    }


}

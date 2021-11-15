package it.unibo.oop.lab.mvcio;

import java.io.File;
/**
 * 
 */
public class Controller {

    private File current;
    /**
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * @param current : current file
     */
    public void setCurrentFile(final File current) {
        this.current = current;
    }
     /** 2) A method for getting the current File.
      * 
      * @return the current file
     */
    public File getCurrentFile() {
        return this.current;
    }
     /** 3) A method for getting the path (in form of String) of the current File.
      * 
      * @param current : current file
      * @return the absolute path
     */ 
    public String getFilePath(final File current) {
        return this.current.getAbsolutePath();
    }
     /** 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * @param string : string to write in file
     * @param current : file to write into
     */ 
    public void writeFile(final String string, final File current) {
    }
     /* 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */

}

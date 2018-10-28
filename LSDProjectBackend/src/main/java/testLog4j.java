
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Micha
 */
public class testLog4j {

    private static final Logger logger = Logger.getLogger(testLog4j.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        logger.info("HEJSA!");
        logger.info("I LOG!");
    }
}

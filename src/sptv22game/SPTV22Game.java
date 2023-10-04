/*
 * Для запуска в Windows необходимо создать bat файл с таким содержимым:
 * start "" java -jar JPTV22Game.jar jptv22Game.JPTV22Game
 */
package sptv22game;

/**
 *
 * @author Melnikov
 */
public class SPTV22Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        App app;
        app = new App();
        app.run();
    }
    
}

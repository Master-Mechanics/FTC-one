package org.firstinspires.ftc.teamcode;

public class VuMarkSettings {

    /*
     * Gray = true
     * Brown = false
     *
     * Frog = 0 && 1
     * Bird = 2 && 3
     * Snake = 4 && 5
     */

    public static final boolean[][][] layouts = new boolean[][][]
    {
            // Frog 0
            {
                    {true, false, true},
                    {false, true, false},
                    {true, false, true},
                    {false, true, false}
            },
            // Frog 1
            {
                    {false, true, false},
                    {true, false, true},
                    {false, true, false},
                    {true, false, true}
            },
            // Bird 2
            {
                    {true, false, true},
                    {false, true, false},
                    {false, true, false},
                    {true, false, true}
            },
            // Bird 3
            {
                    {false, true, false},
                    {true, false, true},
                    {true, false, true},
                    {false, true, false}
            },
            // Snake 4
            {
                    {false, true, true},
                    {false, false, true},
                    {true, false, false},
                    {true, true, false}
            },
            // Snake 5
            {
                    {true, false, false},
                    {true, true, false},
                    {false, true, true},
                    {false, false, true}
            }
    };

    /*
     * 'l' -> left
     * 'c' -> center
     * 'r' -> right
     */
    public VuMarkSettings(char mark) {
    }
}

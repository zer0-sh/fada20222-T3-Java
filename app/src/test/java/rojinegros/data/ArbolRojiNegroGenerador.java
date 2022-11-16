package rojinegros.data;

import rojinegros.ArbolRojinegro;

public class ArbolRojiNegroGenerador {
    /*
     * Ejemplo1 para rotación a la izquierda.
     */
    public ArbolRojinegro ejemplo1() {
        ArbolRojinegro ejemplo = new ArbolRojinegro(
                new ArbolRojinegro(
                        null,
                        null,
                        1,
                        true
                ),
                new ArbolRojinegro(
                        new ArbolRojinegro(
                                null,
                                null,
                                3,
                                true
                        ),
                        new ArbolRojinegro(
                                null,
                                null,
                                5,
                                true
                        ),
                        4,
                        false
                ),
                2,
                true
        );
        return ejemplo;
    }

    /*
     * Ejemplo2 para rotación a la izquierda.
     */
    public ArbolRojinegro ejemplo2() {
        ArbolRojinegro ejemplo = new ArbolRojinegro(
                new ArbolRojinegro(
                        null,
                        null,
                        1,
                        true
                ),
                new ArbolRojinegro(
                        new ArbolRojinegro(
                                null,
                                null,
                                6,
                                true
                        ),
                        new ArbolRojinegro(
                                null,
                                null,
                                9,
                                true
                        ),
                        8,
                        false
                ),
                5,
                true
        );
        return ejemplo;
    }


    /*
     * Ejemplo3 para rotación a la derecha.
     */
    public ArbolRojinegro ejemplo3() {
        ArbolRojinegro ejemplo = new ArbolRojinegro(

                new ArbolRojinegro(
                        new ArbolRojinegro(
                                null,
                                null,
                                1,
                                true
                        ),
                        new ArbolRojinegro(
                                null,
                                null,
                                3,
                                true
                        ),
                        2,
                        false
                ),
                new ArbolRojinegro(
                        null,
                        null,
                        5,
                        true
                ),
                4,
                true
        );
        return ejemplo;
    }


    /*
     * Ejemplo4 para rotación a la derecha.
     */
    public ArbolRojinegro ejemplo4() {
        ArbolRojinegro ejemplo = new ArbolRojinegro(

                new ArbolRojinegro(
                        new ArbolRojinegro(
                                null,
                                null,
                                1,
                                true
                        ),
                        new ArbolRojinegro(
                                null,
                                null,
                                6,
                                true
                        ),
                        5,
                        false
                ),
                new ArbolRojinegro(
                        null,
                        null,
                        9,
                        true
                ),
                8,
                true
        );
        return ejemplo;
    }
}

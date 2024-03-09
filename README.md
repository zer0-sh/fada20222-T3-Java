# Taller 3 
 ## Arboles rojinegroes

## Recursos

http://www.rmboot.com/RedBlack.html

## Composición del codigo

    Clase ArbolRojinegro: Define la estructura básica de un nodo en el árbol rojinegro. Cada nodo tiene referencias a su hijo  
    izquierdo (izq) y derecho (der), su valor (valor), y un atributo de color que indica si es negro o rojo (black).

    Métodos de Inserción:
        insertar(int x): Inserta un nuevo nodo con el valor especificado en el árbol rojinegro. Mantiene las propiedades del árbol  
        rojinegro después de la inserción.
        arreglarInsercion(ArbolRojinegro nodo): Método auxiliar para mantener las propiedades del árbol rojinegro después de la  
        inserción de un nuevo nodo.

    Métodos de Búsqueda:
        search(int valorarbol): Busca un valor específico en el árbol y devuelve el nodo que lo contiene, si existe.
        maximo(): Encuentra el valor máximo en el árbol.
        minimo(): Encuentra el valor mínimo en el árbol.

    Rotaciones:
        rotacionIzquierda(int x): Realiza una rotación izquierda en el árbol con respecto al nodo que tiene el valor especificado.
        rotacionDerecha(int x): Realiza una rotación derecha en el árbol con respecto al nodo que tiene el valor especificado.

    Otros Métodos:
        inorden(): Realiza un recorrido en orden en el árbol y devuelve una representación de los valores en forma de cadena.
        bfs(): Realiza una búsqueda por amplitud en el árbol y devuelve una representación de los valores en forma de cadena.

El código también proporciona áreas de pruebas para verificar la estructura y el orden del árbol, así como métodos auxiliares para  
mantener las propiedades del árbol rojinegro.

## Cambios finales
Se hizo el merge de todo lo trabajado
Se ha agregado una referencia al padre en las pruebas, puede hacer este cambio para tener el nodo padre

```java
@Getter
@Setter
private ArbolRojinegro father;
```
Integrantes:    Johan Muñoz  
Sebastian Giraldo  
Daniel Ospina  

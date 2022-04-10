package marmia.algorithm;

import java.util.Arrays;

public class MagicCircleOfMarmiaAlgorithm {
    private Node head = null;
    private Node tail = null;
    private Node active = null;
    private int indexOfActive = 0;
    private int count = 0;

    private class Node {
        protected Integer element;
        protected Node next;
        protected Node prev;

        protected Node(Integer e) {
            element = e;
        }
    }

    public void createMagicCircle(int quantity) {
        for (int i = 0; i < quantity; i++) {
            this.insertInMCOM(i);
            if (i <= 20) {
                System.out.println(Arrays.toString(this.subList(0, i + 1)));
            }
        }
    }

    public String getKingNeighbors() {
        return "OS VIZINHOS DO REI: " + this.head.next.element + " E " + this.tail.element;
    }

    /**
     * Retorna um arranjo com uma copia de um subconjunto dos elementos da
     * lista, destacando o elemento ativo.
     *
     * @param fromIndex a posição inicial (inclusiva)
     * @param toIndex   a posição final (exclusiva)
     * @return um arranjo com um subconjunto da lista, destacando o elemento ativo.
     * @throws IndexOutOfBoundsException se (fromIndex < 0 || toIndex > size())
     * @throws IllegalArgumentException  se (fromIndex > toIndex)
     */
    private String[] subList(int fromIndex, int toIndex) {
        // Primeiro verifica se os indices sao validos
        if (fromIndex < 0 || toIndex > size())
            throw new IndexOutOfBoundsException();
        if (fromIndex > toIndex)
            throw new IllegalArgumentException();

        // Cria um array com (toIndex-fromIndex) posicoes
        String a[] = new String[toIndex - fromIndex];

        // "Caminha" na lista ate a posicao fromIndex
        Node aux = this.getNode(fromIndex);

        // Copiar os elementos entre fromIndex e toIndex para o array
        for (int i = 0; i < a.length; i++) {
            if (this.active.element.toString().equals(aux.element.toString())) {
                a[i] = "[" + aux.element + "]";
            } else {
                a[i] = aux.element.toString();
            }
            aux = aux.next;
        }
        return a;
    }

    /**
     * # Insere uma quantidade de pessoas no círculo mágico de Mármia, conforme as seguintes instruções:
     * O elemento inicial é adicionado e se torna o ativo
     * Os próximos elementos são adicionados na posição definida pela soma dos valores vizinhos do ativo na lista circular
     *
     * @param element Quantidade de pessoas no círculo
     */
    private void insertInMCOM(int element) {
        if (this.isEmpty()) {
            this.active = this.add(element);
            this.indexOfActive = 0;
        } else {
            int steps = this.active.prev.element + this.active.next.element;
            int auxIndex = ((this.indexOfActive + steps) % count + 1);
            this.active = this.add(auxIndex, element);
            this.indexOfActive = auxIndex;
        }
    }

    /**
     * @param index Posição
     * @return Nodo na posição index
     */
    private Node getNode(int index) {
        Node aux;
        if (index == 0) {
            return head;
        }
        // Se o index está na primeira metade da lista
        if (index <= count / 2) {
            aux = head; // Auxiliar começa da head
            for (int i = 0; i < index; i++) { // Enquanto não encontrar o index
                aux = aux.next; // Auxiliar se torna o nodo sucessor
            }
        } // Se o index está na segunda metade da lista
        else {
            aux = tail;
            for (int i = count - 1; i > index; i--) // Enquanto não encontrar o index
                aux = aux.prev; // Auxiliar se torna o nodo anterior
        }
        return aux;
    }

    /**
     * Insere um elemento em uma determinada posição da lista
     *
     * @param index   Posição na lista
     * @param element Elemento a ser inserido
     */
    private Node add(int index, Integer element) throws IndexOutOfBoundsException {
        if (index == count) {
            return this.add(element);
        } else {
            Node n = new Node(element);
            Node aux = this.getNode(index);
            // Configura os ponteiros do nodo a ser inserido
            n.prev = aux.prev;
            n.next = aux;
            // Modifica os ponteiros do nodo que estava na posição index e de seu antecessor
            aux.prev.next = n;
            aux.prev = n;
            // Incrementa o index
            count++;
            tail.next = head;
            head.prev = tail;
            return n;
        }
    }

    /**
     * Adiciona um elemento ao final da lista
     *
     * @param element Elemento a ser adicionado
     * @return Retorna o nó adicionado
     */
    private Node add(Integer element) {
        Node n = new Node(element);
        if (head == null) {
            head = tail = n;
            head.prev = tail;
            head.next = tail;
        } else {
            tail.next = n;
            n.prev = tail;
            tail = n;
        }
        tail.next = head;
        head.prev = tail;
        count++;
        return n;
    }

    private int size() {
        return count;
    }

    private boolean isEmpty() {
        return (count == 0);
    }
}
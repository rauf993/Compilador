/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package traductor_lexico;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fer-m
 */
public class Node {
    String type;
    String value;
    List<Node> children;

    public Node(String type, String value) {
        this.type = type;
        this.value = value;
        this.children = new ArrayList<>();
    }
}

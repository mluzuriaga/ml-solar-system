package com.mercadopago.utils;

import javafx.geometry.Point2D;

public class TriangleMath {

    // 6259.9148
    public static double MAXIMUM_PERIMETER = 6262.3003542392635;

    /**
     * Calcula el perimetro de un triangulo
     *
     * @param point1 -
     * @param point2 -
     * @param point3 -
     * @return -
     */
    public static double trianglePerimeter(Point2D point1, Point2D point2, Point2D point3) {

        double distance12 = point1.distance(point2);
        double distance23 = point2.distance(point3);
        double distance31 = point3.distance(point1);

        return distance12 + distance23 + distance31;

    }

}

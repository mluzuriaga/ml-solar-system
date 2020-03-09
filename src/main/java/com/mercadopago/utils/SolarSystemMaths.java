package com.mercadopago.utils;

import javafx.geometry.Point2D;

import java.text.DecimalFormat;
import java.util.List;

import static java.lang.Math.*;
import static java.lang.StrictMath.sin;

public class SolarSystemMaths {

    /**
     * Obtengo el punto X/Y sobre la circunferencia de un circulo a partir de su angulo y radius
     *
     * @param angle  - angulo
     * @param radius - radio
     * @return - retorna el punto X/Y
     */
    public static Point2D getCircumferencePoint(int angle, long radius) {

        // Tomo hasta 8 decimales para no introducir errores
        DecimalFormat df = new DecimalFormat("#.########");

        // A partir del angulo en el que se encuentra el planeta obtengo las posiciones x e y
        double x = cos(toRadians(angle)) * radius;
        x = Double.parseDouble(df.format(x).replace(",", "."));

        double y = sin(toRadians(angle)) * radius;
        y = Double.parseDouble(df.format(y).replace(",", "."));

        return new Point2D(x, y);

    }

    /**
     * Calcula si la lista de puntos forman o no una linea
     *
     * @param pointList - lista de puntos
     * @return -
     */
    public static boolean isLine(List<Point2D> pointList) {

        // Al menos necesito dos puntos para el calculo
        if (pointList.size() < 2)
            return false;

        // Obtengo los primeros dos
        Point2D point0 = pointList.get(0);
        Point2D point1 = pointList.get(1);

        // Calculo la pendiente entre ambos
        double gSlope = slope(point0, point1);

        // Calculo entre un punto y su siguiente la pendiente, y voy comparando pendientes.
        // Si alguna se encuentra distinta a la anterior entonces seguro no es una linea recta
        for (int i = 1; i < pointList.size(); i++) {

            Point2D pointI = pointList.get(i);
            double slope = slope(point0, pointI);

            // Porcentaje de error en calculos, al convertir de angulo a punto cartesiano
            if (abs(gSlope - slope) > 0.1)
                return false;

        }

        return true;

    }

    /**
     * Calcula la pendiente entre dos puntos dados
     *
     * @param point1 -
     * @param point2 -
     * @return -
     */
    private static double slope(Point2D point1, Point2D point2) {

        // Validacion por infinito
        if ((point1.getX() - point2.getX()) == 0)
            return 0;

        // Calculo de pendiente
        return (point2.getY() - point1.getY()) / (point2.getX() - point1.getX());

    }

    /**
     * Calcula si el punto dado se encuentra dentro del poligono formado por la lista de puntos
     *
     * @param points -
     * @param point  -
     * @return -
     */
    public static boolean isInside(List<Point2D> points, Point2D point) {

        int i;
        int j;
        boolean result = false;

        for (i = 0, j = points.size() - 1; i < points.size(); j = i++) {

            if ((points.get(i).getY() > point.getY()) != (points.get(j).getY() > point.getY()) &&
                    (point.getX() < (points.get(j).getX() - points.get(i).getX()) * (point.getY() - points.get(i).getY()) / (points.get(j).getY() - points.get(i).getY()) + points.get(i).getX())) {
                result = !result;
            }

        }

        return result;

    }

}

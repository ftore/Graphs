package v2;

import java.util.Objects;

public class Distance implements Comparable<Distance> {
    private int source;
    private Double distance;

    public Distance(int s, double d) {
        this.source = s;
        this.distance = d;
    }

    @Override
    public int compareTo(Distance o) {
        return this.distance.compareTo(o.distance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance = (Distance) o;
        return source == distance.source;
    }

    @Override
    public int hashCode() {

        return Objects.hash(source);
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}

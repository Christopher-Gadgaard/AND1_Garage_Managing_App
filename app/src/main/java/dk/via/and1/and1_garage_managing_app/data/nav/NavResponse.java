package dk.via.and1.and1_garage_managing_app.data.nav;

import androidx.fragment.app.Fragment;

import java.util.List;

public class NavResponse {
    List<Rows> rows;
    private List<String> destination_addresses;
    private List<String> origin_addresses;

    public List<String> getDestination_addresses() {
        return destination_addresses;
    }

    public List<String> getOrigin_addresses() {
        return origin_addresses;
    }

    public List<Rows> getRows() {
        return rows;
    }

    static class Rows {
        List<Element> elements;

        public List<Element> getElements() {
            return elements;
        }
    }


    static class Element {

        Distance distance;
        Duration duration;

        public Distance getDistance() {
            return distance;
        }

        public Duration getDuration() {
            return duration;
        }
    }

    static class Distance {
        String text;
        String value;

        public String getText() {
            return text;
        }

        public String getValue() {
            return value;
        }
    }

    static class Duration {
        String text;
        String value;

        public String getText() {
            return text;
        }

        public String getValue() {
            return value;
        }

    }

    public String getDistance() {
        return rows.get(0).elements.get(0).distance.text;
    }

    public String getDuration() {
        return rows.get(0).elements.get(0).duration.text;
    }
}
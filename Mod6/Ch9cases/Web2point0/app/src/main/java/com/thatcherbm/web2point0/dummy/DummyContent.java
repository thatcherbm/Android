package com.thatcherbm.web2point0.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    static {
        addItem(new DummyItem("1", "Classtell", "http://classtell.com/"));
        addItem(new DummyItem("2", "GoAnimate", "https://goanimate4schools.com/public_index"));
        addItem(new DummyItem("3", "Little Bird Tales", "https://littlebirdtales.com/"));
        addItem(new DummyItem("4", "Prezi", "http://edu.prezi.com/"));
        addItem(new DummyItem("5", "Spelling City", "http://www.spellingcity.com/"));
        addItem(new DummyItem("6", "ToonDoo Spaces", "http://www.toondoospaces.com/"));

    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String item_name;
        public final String item_url;


        public DummyItem(String id, String item_name, String item_url) {
            this.id = id;
            this.item_name = item_name;
            this.item_url = item_url;
            this.content = item_name;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}

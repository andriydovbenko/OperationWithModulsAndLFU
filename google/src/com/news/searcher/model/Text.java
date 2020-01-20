package com.news.searcher.model;

public enum Text {
    VIRUS("China virus cases up sharply as infecrion spreads"),
    TRUMP("Trump lawyers say impeachment charges dangerous"),
    BEIRUT("Lebanon protests: Anti-government protesters clash with police"),
    COFFEE("In just a few centuries, the world has developed a two-billion-cups-a-day habit."),
    AUSTRALIA("Melbourne and Canberra have been pelted by golf-ball sized hail in" +
            " separate storms within 24 hours."),
    VOLCANO("People living at the foot of a volcano in Ecuador are being prepared " +
            "in case it starts billowing out ash and spewing lava."),
    UK("The exact circumstances of how 39 people came to be found dead " +
            "in a lorry container on an industrial estate in Essex are yet to be confirmed"),
    RIVER("In New Zealand, the Whanganui River was recently granted the legal status of a person."),
    WORLD("In this newtopia, former UN climate chief Christiana Figueres " +
            "outlines her vision of a world that's entirely free of fossil fuels."),
    CLIMAT("Climate changed. That's all.");

    private String info;

    Text(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}

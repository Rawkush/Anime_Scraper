package com.gecdevelopers.scrapper.Models;

public class AnimeListModel {
    String Type,PlotSummary,Genre,Released,Status,Name;
    public AnimeListModel()
    {

    }
    public AnimeListModel(String type, String plotSummary, String genre, String released, String status,String name) {
        Type = type;
        PlotSummary = plotSummary;
        Genre = genre;
        Released = released;
        Status = status;
        Name=name;
    }

    public String getType() {
        return Type;
    }
    public  String getName()
    {
        return Name;

    }
    public void setName(String name)
    {
        Name=name;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPlotSummary() {
        return PlotSummary;
    }

    public void setPlotSummary(String plotSummary) {
        PlotSummary = plotSummary;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String released) {
        Released = released;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}

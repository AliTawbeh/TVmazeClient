package com.brightcove.tvmazeclient.data.model;

/**
 * Created by Ali on 12-Apr-18.
 */

public class Show {
    private int id;
    private String url;
    private String name;
    private String type;
    private String language;
    private String[] genres;
    private String status;
    private int runtime;
    private String primiered;
    private String officialSite;
    private ShowSchedule schedule;
    private ShowRating rating;
    private int weight;
    private ShowNetwork network;
    private ShowWebChannel webChannel;
    private ShowExternals externals;
    private ImageRef image;
    private String summary;
    private int updated;
    private ShowLinks _links;

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    public String[] getGenres() {
        return genres;
    }

    public String getStatus() {
        return status;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getPrimiered() {
        return primiered;
    }

    public String getOfficialSite() {
        return officialSite;
    }

    public ShowSchedule getSchedule() {
        return schedule;
    }

    public ShowRating getRating() {
        return rating;
    }

    public int getWeight() {
        return weight;
    }

    public ShowNetwork getNetwork() {
        return network;
    }

    public ShowWebChannel getWebChannel() {
        return webChannel;
    }

    public ShowExternals getExternals() {
        return externals;
    }

    public ImageRef getImage() {
        return image;
    }

    public String getSummary() {
        return summary;
    }

    public int getUpdated() {
        return updated;
    }

    public ShowLinks get_links() {
        return _links;
    }
}

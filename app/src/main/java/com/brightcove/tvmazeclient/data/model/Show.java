package com.brightcove.tvmazeclient.data.model;

import android.arch.persistence.room.Embedded;

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
    @Embedded(prefix = "ShowSchedule")
    private ShowSchedule schedule;
    @Embedded(prefix = "ShowRating")
    private ShowRating rating;
    private int weight;
    @Embedded(prefix = "ShowNetwork")
    private ShowNetwork network;
    @Embedded(prefix = "ShowWebChannel")
    private ShowWebChannel webChannel;
    @Embedded(prefix = "ShowExternals")
    private ShowExternals externals;
    @Embedded(prefix = "ImageRef")
    private ImageRef image;
    private String summary;
    private int updated;
    @Embedded(prefix = "ShowLinks")
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

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public void setPrimiered(String primiered) {
        this.primiered = primiered;
    }

    public void setOfficialSite(String officialSite) {
        this.officialSite = officialSite;
    }

    public void setSchedule(ShowSchedule schedule) {
        this.schedule = schedule;
    }

    public void setRating(ShowRating rating) {
        this.rating = rating;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setNetwork(ShowNetwork network) {
        this.network = network;
    }

    public void setWebChannel(ShowWebChannel webChannel) {
        this.webChannel = webChannel;
    }

    public void setExternals(ShowExternals externals) {
        this.externals = externals;
    }

    public void setImage(ImageRef image) {
        this.image = image;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setUpdated(int updated) {
        this.updated = updated;
    }

    public void set_links(ShowLinks _links) {
        this._links = _links;
    }
}

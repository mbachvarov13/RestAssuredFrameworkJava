package resources;

public enum APIResources {
    addPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");
    private String urlPath;

    APIResources(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getURLPath() {
        return urlPath;
    }

}

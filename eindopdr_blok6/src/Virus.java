public class Virus {

    private String virusId;
    private String taxId;
    private String hostName;
    private String classification;

    /**
     * @param virusId
     * @param taxId
     * @param hostName
     * @param classification
     */
    public Virus(String virusId, String taxId, String hostName, String classification) {

            this.virusId = virusId;
            this.taxId = taxId;
            this.hostName = hostName;
            this.classification = classification;
    }

    public String getVirusId() {
        return this.virusId;
    }

    public void setVirusId(String virusId) {
        this.virusId = virusId;
    }

    public String getTaxId() {
        return this.taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getHostName() {
        return this.hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassivication(String classification) {
        this.classification = classification;
    }

    /**
     * @return een een string met virus Id, host Id, host naam en classification.
     */
    public String toString() {
        return virusId + " " + taxId + " " + hostName + " " + classification;
    }
}
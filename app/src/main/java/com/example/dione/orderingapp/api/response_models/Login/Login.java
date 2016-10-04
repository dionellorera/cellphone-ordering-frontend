package com.example.dione.orderingapp.api.response_models.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dione on 29 Sep 2016.
 */

public class Login {
    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;

    /**
     *
     * @return
     * The result
     */
    public Result getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public class Result {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("email_address")
        @Expose
        private String emailAddress;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("contact_number")
        @Expose
        private String contactNumber;
        @SerializedName("gender_id")
        @Expose
        private Integer genderId;
        @SerializedName("branch_id")
        @Expose
        private Integer branchId;
        @SerializedName("verified")
        @Expose
        private Integer verified;
        @SerializedName("date_created")
        @Expose
        private String dateCreated;

        /**
         *
         * @return
         * The id
         */
        public Integer getId() {
            return id;
        }

        /**
         *
         * @param id
         * The id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         *
         * @return
         * The emailAddress
         */
        public String getEmailAddress() {
            return emailAddress;
        }

        /**
         *
         * @param emailAddress
         * The email_address
         */
        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        /**
         *
         * @return
         * The username
         */
        public String getUsername() {
            return username;
        }

        /**
         *
         * @param username
         * The username
         */
        public void setUsername(String username) {
            this.username = username;
        }

        /**
         *
         * @return
         * The password
         */
        public String getPassword() {
            return password;
        }

        /**
         *
         * @param password
         * The password
         */
        public void setPassword(String password) {
            this.password = password;
        }

        /**
         *
         * @return
         * The firstName
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         *
         * @param firstName
         * The first_name
         */
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        /**
         *
         * @return
         * The lastName
         */
        public String getLastName() {
            return lastName;
        }

        /**
         *
         * @param lastName
         * The last_name
         */
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        /**
         *
         * @return
         * The contactNumber
         */
        public String getContactNumber() {
            return contactNumber;
        }

        /**
         *
         * @param contactNumber
         * The contact_number
         */
        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        /**
         *
         * @return
         * The genderId
         */
        public Integer getGenderId() {
            return genderId;
        }

        /**
         *
         * @param genderId
         * The gender_id
         */
        public void setGenderId(Integer genderId) {
            this.genderId = genderId;
        }

        /**
         *
         * @return
         * The branchId
         */
        public Integer getBranchId() {
            return branchId;
        }

        /**
         *
         * @param branchId
         * The branch_id
         */
        public void setBranchId(Integer branchId) {
            this.branchId = branchId;
        }

        /**
         *
         * @return
         * The verified
         */
        public Integer getVerified() {
            return verified;
        }

        /**
         *
         * @param verified
         * The verified
         */
        public void setVerified(Integer verified) {
            this.verified = verified;
        }

        /**
         *
         * @return
         * The dateCreated
         */
        public String getDateCreated() {
            return dateCreated;
        }

        /**
         *
         * @param dateCreated
         * The date_created
         */
        public void setDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
        }

    }
}

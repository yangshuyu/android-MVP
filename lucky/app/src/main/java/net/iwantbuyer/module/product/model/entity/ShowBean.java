package net.iwantbuyer.module.product.model.entity;

import java.util.List;

/**
 * Created by yangshuyu on 2017/5/15.
 */
public class ShowBean {

    /**
     * _resource : Post
     * content : gnmgmgkf
     * created_at : 2017-05-10T06:47:43.684735+00:00
     * game_issue_id : 16
     * id : 90
     * images : ["//static-staging.luckybuyer.net/social/images/95b6bbcc6d5b4e83bfd907da9cd8f793.jpeg"]
     * product_title : 5.00 MYR  Top up your Celcom/Digi/Umobile/Maxis Credit
     * user : {"_resource":"PublicUser","id":648,"profile":{"locale":null,"name":"杨树宇","picture":"https://abs.twimg.com/sticky/default_profile_images/default_profile_normal.png","social_link":"https://twitter.com/intent/user?user_id=818725636785119234","timezone":null}}
     */

    private String _resource;
    private String content;
    private String created_at;
    private int game_issue_id;
    private int id;
    private String product_title;
    private UserBean user;
    private List<String> images;

    public String get_resource() {
        return _resource;
    }

    public void set_resource(String _resource) {
        this._resource = _resource;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getGame_issue_id() {
        return game_issue_id;
    }

    public void setGame_issue_id(int game_issue_id) {
        this.game_issue_id = game_issue_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public static class UserBean {
        /**
         * _resource : PublicUser
         * id : 648
         * profile : {"locale":null,"name":"杨树宇","picture":"https://abs.twimg.com/sticky/default_profile_images/default_profile_normal.png","social_link":"https://twitter.com/intent/user?user_id=818725636785119234","timezone":null}
         */

        private String _resource;
        private int id;
        private ProfileBean profile;

        public String get_resource() {
            return _resource;
        }

        public void set_resource(String _resource) {
            this._resource = _resource;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public ProfileBean getProfile() {
            return profile;
        }

        public void setProfile(ProfileBean profile) {
            this.profile = profile;
        }

        public static class ProfileBean {
            /**
             * locale : null
             * name : 杨树宇
             * picture : https://abs.twimg.com/sticky/default_profile_images/default_profile_normal.png
             * social_link : https://twitter.com/intent/user?user_id=818725636785119234
             * timezone : null
             */

            private Object locale;
            private String name;
            private String picture;
            private String social_link;
            private Object timezone;

            public Object getLocale() {
                return locale;
            }

            public void setLocale(Object locale) {
                this.locale = locale;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getSocial_link() {
                return social_link;
            }

            public void setSocial_link(String social_link) {
                this.social_link = social_link;
            }

            public Object getTimezone() {
                return timezone;
            }

            public void setTimezone(Object timezone) {
                this.timezone = timezone;
            }
        }
    }
}

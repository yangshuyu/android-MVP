package net.iwantbuyer.module.product.model.entity;

import java.util.List;

/**
 * Created by admin on 2016/9/14.
 */
public class GameProductBean {

    /**
     * _resource : type of the resource
     * closed_at : 2017-05-04T05:53:45.167Z
     * finished_at : 2017-05-04T05:53:45.167Z
     * id : 0
     * issue_id : 0
     * left_shares : 0
     * lucky_number : string
     * lucky_order : {"_resource":"type of the resource","created_at":"2017-05-04T05:53:45.167Z","id":0,"numbers":[0],"shares":0,"total_shares":"string","user":{"_resource":"type of the resource","id":0,"profile":{"locale":"string","name":"string","picture":"string","social_link":"string","timezone":"string"}}}
     * lucky_user : {"_resource":"type of the resource","id":0,"profile":{"locale":"string","name":"string","picture":"string","social_link":"string","timezone":"string"}}
     * num_orders : 0
     * product : {"_resource":"type of the resource","detail":"string","detail_image":"string","detail_image_urls":["string"],"id":0,"title":"string","title_image":"string"}
     * result_countdown : 0
     * share_price : 0
     * shares : 0
     * shares_increment : 0
     * start_at : 2017-05-04T05:53:45.167Z
     * status : closed
     * url : string
     */

    private String _resource;
    private String closed_at;
    private String finished_at;
    private int id;
    private int issue_id;
    private int left_shares;
    private String lucky_number;
    private LuckyOrderBean lucky_order;
    private LuckyUserBean lucky_user;
    private int num_orders;
    private ProductBean product;
    private int result_countdown;
    private int share_price;
    private int shares;
    private int shares_increment;
    private String start_at;
    private String status;
    private String url;

    public String get_resource() {
        return _resource;
    }

    public void set_resource(String _resource) {
        this._resource = _resource;
    }

    public String getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(String closed_at) {
        this.closed_at = closed_at;
    }

    public String getFinished_at() {
        return finished_at;
    }

    public void setFinished_at(String finished_at) {
        this.finished_at = finished_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(int issue_id) {
        this.issue_id = issue_id;
    }

    public int getLeft_shares() {
        return left_shares;
    }

    public void setLeft_shares(int left_shares) {
        this.left_shares = left_shares;
    }

    public String getLucky_number() {
        return lucky_number;
    }

    public void setLucky_number(String lucky_number) {
        this.lucky_number = lucky_number;
    }

    public LuckyOrderBean getLucky_order() {
        return lucky_order;
    }

    public void setLucky_order(LuckyOrderBean lucky_order) {
        this.lucky_order = lucky_order;
    }

    public LuckyUserBean getLucky_user() {
        return lucky_user;
    }

    public void setLucky_user(LuckyUserBean lucky_user) {
        this.lucky_user = lucky_user;
    }

    public int getNum_orders() {
        return num_orders;
    }

    public void setNum_orders(int num_orders) {
        this.num_orders = num_orders;
    }

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public int getResult_countdown() {
        return result_countdown;
    }

    public void setResult_countdown(int result_countdown) {
        this.result_countdown = result_countdown;
    }

    public int getShare_price() {
        return share_price;
    }

    public void setShare_price(int share_price) {
        this.share_price = share_price;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public int getShares_increment() {
        return shares_increment;
    }

    public void setShares_increment(int shares_increment) {
        this.shares_increment = shares_increment;
    }

    public String getStart_at() {
        return start_at;
    }

    public void setStart_at(String start_at) {
        this.start_at = start_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class LuckyOrderBean {
        /**
         * _resource : type of the resource
         * created_at : 2017-05-04T05:53:45.167Z
         * id : 0
         * numbers : [0]
         * shares : 0
         * total_shares : string
         * user : {"_resource":"type of the resource","id":0,"profile":{"locale":"string","name":"string","picture":"string","social_link":"string","timezone":"string"}}
         */

        private String _resource;
        private String created_at;
        private int id;
        private int shares;
        private String total_shares;
        private UserBean user;
        private List<Integer> numbers;

        public String get_resource() {
            return _resource;
        }

        public void set_resource(String _resource) {
            this._resource = _resource;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getShares() {
            return shares;
        }

        public void setShares(int shares) {
            this.shares = shares;
        }

        public String getTotal_shares() {
            return total_shares;
        }

        public void setTotal_shares(String total_shares) {
            this.total_shares = total_shares;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public List<Integer> getNumbers() {
            return numbers;
        }

        public void setNumbers(List<Integer> numbers) {
            this.numbers = numbers;
        }

        public static class UserBean {
            /**
             * _resource : type of the resource
             * id : 0
             * profile : {"locale":"string","name":"string","picture":"string","social_link":"string","timezone":"string"}
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
                 * locale : string
                 * name : string
                 * picture : string
                 * social_link : string
                 * timezone : string
                 */

                private String locale;
                private String name;
                private String picture;
                private String social_link;
                private String timezone;

                public String getLocale() {
                    return locale;
                }

                public void setLocale(String locale) {
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

                public String getTimezone() {
                    return timezone;
                }

                public void setTimezone(String timezone) {
                    this.timezone = timezone;
                }
            }
        }
    }

    public static class LuckyUserBean {
        /**
         * _resource : type of the resource
         * id : 0
         * profile : {"locale":"string","name":"string","picture":"string","social_link":"string","timezone":"string"}
         */

        private String _resource;
        private int id;
        private ProfileBeanX profile;

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

        public ProfileBeanX getProfile() {
            return profile;
        }

        public void setProfile(ProfileBeanX profile) {
            this.profile = profile;
        }

        public static class ProfileBeanX {
            /**
             * locale : string
             * name : string
             * picture : string
             * social_link : string
             * timezone : string
             */

            private String locale;
            private String name;
            private String picture;
            private String social_link;
            private String timezone;

            public String getLocale() {
                return locale;
            }

            public void setLocale(String locale) {
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

            public String getTimezone() {
                return timezone;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }
        }
    }

    public static class ProductBean {
        /**
         * _resource : type of the resource
         * detail : string
         * detail_image : string
         * detail_image_urls : ["string"]
         * id : 0
         * title : string
         * title_image : string
         */

        private String _resource;
        private String detail;
        private String detail_image;
        private int id;
        private String title;
        private String title_image;
        private List<String> detail_image_urls;

        public String get_resource() {
            return _resource;
        }

        public void set_resource(String _resource) {
            this._resource = _resource;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getDetail_image() {
            return detail_image;
        }

        public void setDetail_image(String detail_image) {
            this.detail_image = detail_image;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle_image() {
            return title_image;
        }

        public void setTitle_image(String title_image) {
            this.title_image = title_image;
        }

        public List<String> getDetail_image_urls() {
            return detail_image_urls;
        }

        public void setDetail_image_urls(List<String> detail_image_urls) {
            this.detail_image_urls = detail_image_urls;
        }
    }
}

package com.cnsky1103.model.picture;

import java.util.List;

/**
 * 由网络获得的随机图片
 * @author sky
 */
public class Picture {
    private String img;
    private String dy_url;
    private List<Tag> tags;
    private Owner owner;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDy_url() {
        return dy_url;
    }

    public void setDy_url(String dy_url) {
        this.dy_url = dy_url;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public static class Tag {
        private String tag_id;
        private String tag_title;

        public String getTag_id() {
            return tag_id;
        }

        public void setTag_id(String tag_id) {
            this.tag_id = tag_id;
        }

        public String getTag_title() {
            return tag_title;
        }

        public void setTag_title(String tag_title) {
            this.tag_title = tag_title;
        }
    }

    public static class Owner {
        private String name;
        private String uid;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}

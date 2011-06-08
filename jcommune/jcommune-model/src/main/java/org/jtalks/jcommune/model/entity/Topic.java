/**
 * Copyright (C) 2011  jtalks.org Team
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 * Also add information on how to contact you by electronic and paper mail.
 * Creation date: Apr 12, 2011 / 8:05:19 PM
 * The jtalks.org Project
 */
package org.jtalks.jcommune.model.entity;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the topic of the forum.
 * Contains the list of related {@link Post}.
 * All Posts will be cascade deleted with the associated Topic.
 * The fields creationDate, topicStarter and Title are required and can't be <code>null</code>
 *
 * @author Pavel Vervenko
 * @author Vitaliy Kravchenko
 * @author Max Malakhov
 */
public class Topic extends Persistent {

    /**
     * The creation date of the topic.
     */
    private DateTime creationDate;
    /**
     * The user who create the topic
     */
    private User topicStarter;
    private String title;
    /**
     * The list of topic's posts
     */
    private List<Post> posts = new ArrayList<Post>();

    private Branch branch;
    
    /**
     * The modification date of the topic.
     */
    private DateTime modificationDate;    
    
    /**
     * Creates the Topic instance. All fields values are null.
     */
    public Topic() {
    }

    /**
     * Creates the Topic with the specified creation date.
     *
     * @param creationDate the topic's creation date
     */
    public Topic(DateTime creationDate) {
        this.creationDate = creationDate;
        
        updateModificationDate();
    }

    /**
     * Creates a new Topic with the creationDate initialized with current time.
     *
     * @return newly created Topic
     */
    public static Topic createNewTopic() {
        return new Topic(new DateTime());
    }

    /**
     * Add new {@link Post} to the topic.
     * The method sets Posts.topic field to this Topic.
     *
     * @param newPost post to add
     */
    public void addPost(Post newPost) {
        posts.add(newPost);
        newPost.setTopic(this);
        
        updateModificationDate();
    }

    /**
     * Remove the post from the topic.
     *
     * @param postToRemove post to remove
     */
    public void removePost(Post postToRemove) {
        posts.remove(postToRemove);
        
        updateModificationDate();
    }

    /**
     * Get the post creation date.
     *
     * @return the creationDate
     */
    public DateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Set the post creation date.
     *
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Get the user who created the post.
     *
     * @return the userCreated
     */
    public User getTopicStarter() {
        return topicStarter;
    }

    /**
     * The the author of the post.
     *
     * @param userCreated the user who create the post
     */
    public void setTopicStarter(User userCreated) {
        this.topicStarter = userCreated;
    }

    /**
     * Gets the topic name.
     *
     * @return the topicName
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the topic title.
     *
     * @param newTitle the title to set
     */
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    /**
     * Get the list of the posts.
     *
     * @return the list of posts
     */
    public List<Post> getPosts() {
        return posts;
    }

    /**
     * Set the list of posts
     *
     * @param posts the posts to set
     */
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    /**
     * Get branch that contains the message
     *
     * @return branch that contains the topic
     */
    public Branch getBranch() {
        return branch;
    }

    /**
     * Set branch that contains the message
     *
     * @param branch branch that contains the topic
     */
    public void setBranch(Branch branch) {
        this.branch = branch;
    }


    /**
     * Set the topic modification date.
     *
     * @param modificationDate the modificationDate to set
     */
	public void setModificationDate(DateTime modificationDate) {
		this.modificationDate = modificationDate;
	}    
    
	
    /**
     * Get the topic modification date.
     *
     * @param modificationDate the modificationDate to get
     */
	public DateTime getModificationDate() {
		return modificationDate;
	}
	
    /**
     * Update the topic modification date.
     *
     * @param modificationDate the modificationDate to update
     */    
	private void updateModificationDate() {
		this.modificationDate = new DateTime();
	}	
}

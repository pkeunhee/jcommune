/**
 * Copyright (C) 2011  JTalks.org Team
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
 */
package org.jtalks.jcommune.service.nontransactional;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.Validate;
import org.jtalks.jcommune.model.entity.JCommuneProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Service class for the Forum Logo relate operations
 * @author Andrei Alikov
 */
public class ForumLogoService extends BaseImageService {
    private String defaultLogoPath;
    private String defaultFavIconPathICO;
    private String defaultFavIconPathPNG;
    private static final Logger LOGGER = LoggerFactory.getLogger(ForumLogoService.class);

    /**
     * Create ForumLogoService instance
     *
     * @param imageUtils        object for image processing
     * @param base64Wrapper     to encode/decode logo passed from the client side
     * @param defaultLogoPath path to the default logo image
     * @param logoSizeProperty let us know the limitation of logo max size
     */
    public ForumLogoService(
            ImageUtils imageUtils,
            Base64Wrapper base64Wrapper,
            String defaultLogoPath,
            String defaultFavIconPathICO,
            String defaultFavIconPathPNG,
            JCommuneProperty logoSizeProperty) {
        super(imageUtils, base64Wrapper, logoSizeProperty);
        this.defaultLogoPath = defaultLogoPath;
        this.defaultFavIconPathICO = defaultFavIconPathICO;
        this.defaultFavIconPathPNG = defaultFavIconPathPNG;
    }



    /**
     * Returns default forum logo to be used when custom user image is not set
     *
     * @return byte array-stored image
     */
    public byte[] getDefaultLogo() {
        byte[] result = new byte[0];
        try {
            result = getFileBytes(defaultLogoPath);
        } catch (IOException e) {
            LOGGER.error("Failed to load default logo", e);
        }

        return result;
    }

    /**
     * Gets the default fav icon in the ICO format (for IE browser)
     * @return byte array with icon data
     */
    public byte[] getDefaultIconICO() {
        byte[] result = new byte[0];
        try {
            result = getFileBytes(defaultFavIconPathICO);
        } catch (IOException e) {
            LOGGER.error("Failed to load fav icon in ICO format", e);
        }

        return result;
    }

    /**
     * Gets the default fav icon in the PNG format (for non-IE browser)
     * @return byte array with icon data
     */
    public byte[] getDefaultIconPNG() {
        byte[] result = new byte[0];
        try {
            result = getFileBytes(defaultFavIconPathPNG);
        } catch (IOException e) {
            LOGGER.error("Failed to load default icon in PNG format", e);
        }

        return result;
    }

    private byte[] getFileBytes(String classPath) throws IOException {
        byte[] result = new byte[0];
        ClassPathResource fileClassPathSource = new ClassPathResource(classPath);
        InputStream stream = null;
        try {
            stream = fileClassPathSource.getInputStream();
            result = new byte[stream.available()];
            Validate.isTrue(stream.read(result) > 0);
        }
        finally {
            IOUtils.closeQuietly(stream);
        }
        return result;
    }
}

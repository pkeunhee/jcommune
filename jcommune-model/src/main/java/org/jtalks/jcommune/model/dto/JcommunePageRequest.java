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
package org.jtalks.jcommune.model.dto;

import org.springframework.data.domain.PageRequest;

/**
 * 
 * @author Anuar Nurmakanov
 *
 */
public class JcommunePageRequest extends PageRequest implements JcommunePageable {
    private static final long serialVersionUID = -9054794147449741044L;
    
    /**
     * Creates a new {@link JcommunePageRequest}. 
     * 
     * @param size size of page
     * @param page page number
     */
    public JcommunePageRequest(int page, int size) {
        super(page, size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfFirstItem() {
        return(getPageNumber() - 1) * getPageSize();
    }
}

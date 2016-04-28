/*
 * Copyright 2016 peter.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package onl.area51.networkrail.location;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author peter
 */
public class LocationKey
        implements Comparable<LocationKey>,
                   Serializable
{

    private static final long serialVersionUID = 1L;

    private final String key;
    private final long id;
    private final int hashCode;

    public LocationKey( String key, long id )
    {
        this.key = key;
        this.id = id;
        hashCode = 43 * +(int) (this.id ^ (this.id >>> 32));
    }

    public LocationKey( String key )
    {
        this( key, Objects.hashCode( key ) );
    }

    public LocationKey( long id )
    {
        this( String.valueOf( id ), id );
    }

    public final long getId()
    {
        return id;
    }

    public final String getKey()
    {
        return key;
    }

    @Override
    public final int hashCode()
    {
        return hashCode;
    }

    @Override
    public final boolean equals( Object obj )
    {
        if( obj == null || getClass() != obj.getClass() ) {
            return false;
        }
        final LocationKey other = (LocationKey) obj;
        return Objects.equals( this.key, other.key ) || this.id == other.id;
    }

    @Override
    public int compareTo( LocationKey o )
    {
        return key.compareTo( o.key );
    }

}

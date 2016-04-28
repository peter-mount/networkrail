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

/**
 *
 * @author peter
 */
public class Stanox
        extends LocationKey
        implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * Ensures that the supplied stanox is within the range 0...99999
     * <p>
     * @param stanox stanox
     * <p>
     * @return corrected stanox
     */
    public static long stanox( long stanox )
    {
        if( stanox < 0 )
        {
            return 0;
        }
        if( stanox > 100000 )
        {
            return stanox % 100000;
        }
        return stanox;
    }

    public Stanox( long id )
    {
        super( id );
    }

}

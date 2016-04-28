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
package onl.area51.networkrail.corpus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import uk.trainwatch.util.sql.SQL;

/**
 * Handles access to Corpus
 * <p>
 * @author peter
 */
public enum CorpusManager
{

    INSTANCE;
    private DataSource dataSource;

    void setDataSource( DataSource dataSource )
    {
        this.dataSource = dataSource;
    }

    /**
     * Perform a lookup from CORPUS for a stanox.
     * <p>
     * This may return more than one entry for the same location.
     * <p>
     * @param stanox
     *               <p>
     * @return
     *         <p>
     * @throws SQLException
     */
    public Collection<Corpus> getStanox( int stanox )
            throws SQLException
    {
        return lookup( "stanox", stanox );
    }

    public Collection<Corpus> getNLC( int nlc )
            throws SQLException
    {
        return lookup( "nlc", nlc );
    }

    public Collection<Corpus> getUIC( int uic )
            throws SQLException
    {
        return lookup( "uic", uic );
    }

    public Collection<Corpus> get3Alpha( String talpha )
            throws SQLException
    {
        if( talpha == null || talpha.length() != 3 )
        {
            return Collections.emptyList();
        }
        return lookup( "talpha", talpha.toUpperCase() );
    }

    public Collection<Corpus> getTiploc( String tiploc )
            throws SQLException
    {
        if( tiploc == null )
        {
            return Collections.emptyList();
        }
        return lookup( "tiploc", tiploc.toUpperCase() );
    }

    private Collection<Corpus> lookup( String field, Object value )
            throws SQLException
    {
        if( value == null || "".equals( value ) )
        {
            return Collections.emptyList();
        }
        try( Connection con = dataSource.getConnection() )
        {
            try( PreparedStatement ps = con.prepareStatement( "SELECT * FROM reference.corpus WHERE " + field + "=?" ) )
            {
                ps.setObject( 1, value );
                return SQL.stream( ps, Corpus.fromSQL ).
                        collect( Collectors.toList() );
            }
        }

    }
}

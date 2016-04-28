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
package onl.area51.networkrail.util;

import java.util.function.Function;
import javax.json.JsonObject;
import uk.trainwatch.util.JsonUtils;

/**
 * A train's id, used as a key in db or cache calls
 * <p/>
 * @author peter
 */
public class TrainId
{

    public static final Function<JsonObject, TrainId> FACTORY
                                                      = o -> new TrainId( TrainDate.FACTORY.apply( o ),
                                                                          JsonUtils.getString( o, "trainId", "" ) );

    private TrainDate trainDate;
    private String trainId;

    public TrainId()
    {
    }

    public TrainId( TrainDate trainDate, String trainId )
    {
        this.trainDate = trainDate;
        this.trainId = trainId;
    }

    public TrainDate getTrainDate()
    {
        return trainDate;
    }

    public String getTrainId()
    {
        return trainId;
    }

    public String getHeadCode()
    {
        return getTrainId().
                substring( 2, 6 );
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 31 * hash + (this.trainDate != null ? this.trainDate.hashCode() : 0);
        hash = 31 * hash + (this.trainId != null ? this.trainId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals( Object obj )
    {
        if( obj == null )
        {
            return false;
        }
        if( getClass() != obj.getClass() )
        {
            return false;
        }
        final TrainId other = (TrainId) obj;
        if( this.trainDate != other.trainDate && (this.trainDate == null || !this.trainDate.equals( other.trainDate )) )
        {
            return false;
        }
        if( (this.trainId == null) ? (other.trainId != null) : !this.trainId.equals( other.trainId ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "TrainId[trainDate=" + trainDate + ", trainId=" + trainId + ']';
    }
}

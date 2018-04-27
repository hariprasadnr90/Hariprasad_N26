package com.n26.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatisticsDTO
{

    private Double sum;

    private Double avg;

    private Double min;

    private Double max;
    
    private Long count;

    private StatisticsDTO()
    {
    }


    public StatisticsDTO(Double sum, Double avg, Double min, Double max, Long count) {
		this.sum = sum;
		this.avg = avg;
		this.min = min;
		this.max = max;
		this.count = count;
	}

	public Double getSum() {
		return sum;
	}


	public Double getAvg() {
		return avg;
	}


	public Double getMin() {
		return min;
	}


	public Double getMax() {
		return max;
	}


	public Long getCount() {
		return count;
	}


	public static StatisticsDTOBuilder newBuilder()
    {
        return new StatisticsDTOBuilder();
    }


    
    public static class StatisticsDTOBuilder
    {
        private Long id;
        private Double sum;

        private Double avg;

        private Double min;

        private Double max;
        
        private Long count;


        public StatisticsDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public StatisticsDTOBuilder setSum(Double sum)
        {
            this.sum = sum;
            return this;
        }


        public StatisticsDTOBuilder setAvg(Double avg)
        {
            this.avg = avg;
            return this;
        }

        public StatisticsDTOBuilder setMax(Double max)
        {
            this.max = max;
            return this;
        }
        
        public StatisticsDTOBuilder setMin(Double min)
        {
            this.min = min;
            return this;
        }
        
        public StatisticsDTOBuilder setCount(Long i)
        {
            this.count = i;
            return this;
        }

        public StatisticsDTO createDTO()
        {
            return new StatisticsDTO(sum, avg, max, min, count);
        }

    }
}

package org.example;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class RatingMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {

    private final static IntWritable one = new IntWritable(1);
    private IntWritable rating = new IntWritable();

    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String[] tokens = value.toString().split("\t");
        if (tokens.length == 4) { // Assuming input format is userID, movieID, rating, timestamp
            int ratingValue = Integer.parseInt(tokens[2]);
            rating.set(ratingValue);
            context.write(rating, one);
        }
    }
}

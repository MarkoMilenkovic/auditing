# auditing
Using hibernate POST_COMMIT_UPDATE and POST_COMMIT_INSERT events to store changes in relational DB.
Sending SQS message with updated/created object to consumer (https://github.com/MarkoMilenkovic/auditing-consumer) which is transforming it to json and storing in aws s3 bucket that is used by Glue/Athena.
Using Athena (for now only through AWS console) for quering json.

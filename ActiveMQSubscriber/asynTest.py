import asyncio

async def async_task1():
    print("Task 1 started")
    await asyncio.sleep(5)  # Simulate an asynchronous operation (e.g., I/O operation)
    print("Task 1 completed")

async def async_task2():
    print("Task 2 started")
    await asyncio.sleep(1)  # Simulate another asynchronous operation
    print("Task 2 completed")

async def main():
    try:
        print("Main task started")

        # Simulate running both async tasks concurrently
        task1 = asyncio.create_task(async_task1())
        task2 = asyncio.create_task(async_task2())

        # Wait for both tasks to complete
        await asyncio.gather(task1, task2)

    except Exception as e:
        print(f"Error in main task: {e}")
    finally:
        print("Executing finally block")

        # Ensure that we await each task to completion
        await task1  # Wait for async_task1 to complete
        await task2  # Wait for async_task2 to complete

        print("All tasks completed")

# Run the main coroutine
asyncio.run(main())

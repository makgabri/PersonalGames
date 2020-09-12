import pygame
import random
from snake import Snake

# Initializing Pygame
pygame.init()

# Game parameters
WIDTH = 300
OBJECT_RADIUS = 10
MAP_SIZE = WIDTH // (2 * OBJECT_RADIUS)
TICK = 50
GAME_UPDATE = 1

# Screen
screen = pygame.display.set_mode((WIDTH, WIDTH))
pygame.display.set_caption("Snake Game")

# Colors
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
GRAY = (200, 200, 200)
RED = (255, 0, 0)
BLUE = (0, 0, 255)

# Fonts
END_FONT = pygame.font.SysFont('courier', 40)


# Helpers
def draw_snake_head(head):
    """
    Draw snake head onto screen
    :param head: the positional coordinates of the snakes head
    """
    pygame.draw.circle(screen, BLUE, vector_to_position(head), OBJECT_RADIUS)


def erase_snake_tail(tail):
    """
    Erase snakes tail from screen
    :param tail: the positional coordinates of the snakes tail to remove
    """
    pygame.draw.circle(screen, BLACK, vector_to_position(tail), OBJECT_RADIUS)


def draw_food(food):
    """
    Draws the food onto the screen
    :param food: the positional coordinates of the food
    """
    pygame.draw.circle(screen, RED, point_to_position(food), OBJECT_RADIUS)


def vector_to_position(vector):
    """
    Converts a vector coordinate to a positional coordinate
    :param vector: the vector coordinates of the object
    :return: the positional vector of the object
    """
    return ((vector[0] * OBJECT_RADIUS * 2) + OBJECT_RADIUS,
            (vector[1] * OBJECT_RADIUS * 2) + OBJECT_RADIUS)


def point_to_position(point):
    """
    Converts a point to a positional coordinate
    :param point: the 1d point representing a 2d position
    :return: the positional coordinates of the object
    """
    return (((point // MAP_SIZE) * (OBJECT_RADIUS * 2)) + OBJECT_RADIUS,
            ((point % MAP_SIZE) * (OBJECT_RADIUS * 2)) + OBJECT_RADIUS)


def vector_to_point(vector):
    """
    Converts a vector coordinate to a point
    :param vector: the vector coordinates of the object
    :return: the 1d point representing a 2d position
    """
    return (vector[0] * MAP_SIZE) + vector[1]


def main():
    # Start a delay
    pygame.time.delay(300)

    # Define game state
    paused = False
    update = 0

    # Initializing snake global variables
    snake = Snake(MAP_SIZE)
    unavailable_slots = [vector_to_point(snake.get_head())]
    new_direction = snake.get_direction()

    # Randomize snake food
    food = random.choice(list(set(range(0, (MAP_SIZE*MAP_SIZE)))-set(unavailable_slots)))

    # Draw food and snake
    draw_snake_head(snake.get_head())
    draw_food(food)
    pygame.display.update()

    while snake.is_alive():
        # Game speed/move speed
        pygame.time.delay(TICK)

        # Check for user input
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                quit()
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_RIGHT:
                    if snake.get_direction() != 'left':
                        new_direction = 'right'
                elif event.key == pygame.K_LEFT:
                    if snake.get_direction() != 'right':
                        new_direction = 'left'
                elif event.key == pygame.K_UP:
                    if snake.get_direction() != 'down':
                        new_direction = 'up'
                elif event.key == pygame.K_DOWN:
                    if snake.get_direction() != 'up':
                        new_direction = 'down'
                elif event.key == pygame.K_p:
                    paused = not paused

        if not paused:
            if update != GAME_UPDATE:
                update += 1
            else:
                # Update snake
                update = 0
                snake.set_direction(new_direction)

                # Move snake, draw snake head and add head to unvailable slots
                new_head = snake.move()
                draw_snake_head(new_head)
                unavailable_slots.append(vector_to_point(new_head))

                if vector_to_point(new_head) == food:
                    # If snake ate food, check if game won
                    if snake.length == (MAP_SIZE*MAP_SIZE):
                        break
                    # Otherwise add length of snake and randomize new food slot
                    snake.add_length()
                    food = random.choice(list(set(range(0, (MAP_SIZE*MAP_SIZE)))-set(unavailable_slots)))
                    draw_food(food)
                else:
                    # Snake did not eat tail so remove tail
                    old_tail = snake.remove_tail()
                    erase_snake_tail(old_tail)
                    unavailable_slots.remove(vector_to_point(old_tail))
                pygame.display.update()

    pygame.time.delay(500)
    screen.fill(WHITE)
    if snake.length == (MAP_SIZE*MAP_SIZE):
        text = END_FONT.render('YOU WIN!', True, BLACK)
        text_rect = text.get_rect()
        text_rect.center = (WIDTH // 2, WIDTH // 2)
        screen.blit(text, text_rect)
        pygame.display.update()
        print('Player wins')
    else:
        text = END_FONT.render('YOU LOSE!', True, BLACK)
        text_rect = text.get_rect()
        text_rect.center = (WIDTH // 2, WIDTH // 2)
        screen.blit(text, text_rect)
        pygame.display.update()
        print('Player died at: ' + str(snake.get_head()))

    pygame.time.delay(1000)
    pygame.quit()


if __name__ == '__main__':
    main()
